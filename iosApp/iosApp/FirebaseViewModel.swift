//
//  FirebaseViewModel.swift
//  iosApp
//
//  Created by Samuel Luis on 14/11/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared

class FirebaseViewModel: ObservableObject {
    @Published var notes: [Note] = []
    private let firebaseHelper = FirebaseHelper()

    func loadNotes() {
        DispatchQueue.main.async {
            Task {
                do {
                    let notes = try await self.firebaseHelper.getNotes()
                    DispatchQueue.main.async {
                        self.notes = notes
                    }
                } catch {
                    print("Error fetching notes: \(error.localizedDescription)")
                }
            }
        }
    }
}
