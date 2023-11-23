package com.example.todoappkmm.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.todoappkmm.presentation.CreateNoteScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CreateNoteScreen(viewModel: CreateNoteScreenViewModel = getViewModel(), navController: NavController) {
    Scaffold() {
        Box(modifier = Modifier.padding(it)) {
            Text("This is your CreateNoteScreen")
        }
    }
}