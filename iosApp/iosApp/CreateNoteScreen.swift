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

struct CreateNoteScreen : View {
    @State private var title = ""
    @State private var text = ""
    
    var viewModel: CreateNoteScreenViewModel = KoinHelper().getCreateNoteScreenViewModel()
    
    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text("Title:")
            TextField("Enter title", text: $title)
                .textFieldStyle(RoundedBorderTextFieldStyle())

            Text("Text:")
            TextField("Enter text", text: $text)
                .textFieldStyle(RoundedBorderTextFieldStyle())
            
            Button(action: {
                // Perform some action when the button is tapped
                // For example, you can save the note with the entered title and text
                viewModel.createNote(title: title, text: text)
            }) {
                Text("Save Note")
                    .padding()
                    .foregroundColor(.white)
                    .background(Color.blue)
                    .cornerRadius(8)
            }
            Spacer()
        }
        .padding()
        .navigationBarTitle("Create Note")
    }
}

