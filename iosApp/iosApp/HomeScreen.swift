//
//  CreateNoteScreen.swift
//  iosApp
//
//  Created by Samuel Luis on 23/11/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Foundation
import shared

struct HomeScreen : View {
    @ObservedObject private var observableState = ObservableHomeScreenViewModelState()

    var body: some View {
        VStack {
            if !$observableState.notes.isEmpty {
                List(observableState.notes, id: \.title) { note in
                    VStack(alignment: .leading) {
                        Text(note.title)
                            .font(.headline)
                        Text(note.text)
                            .font(.subheadline)
                    }
                }
            } else {
                //todo insert loader here
                Text("Loading...")
                //ActivityIndicator(isAnimating = true)
            }
        }
    }
}


class ObservableHomeScreenViewModelState: ObservableObject {
    @Published var viewModel: HomeScreenViewModel = KoinHelper().getHomeScreenViewModel()
    @Published var notes: [Note] = []

    init() {
        self.viewModel.listOfNotes.addObserver { (value) in
            self.notes = value as? [Note] ?? []
        }
    }
}
