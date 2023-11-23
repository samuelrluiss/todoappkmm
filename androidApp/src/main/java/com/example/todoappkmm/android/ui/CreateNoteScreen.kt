package com.example.todoappkmm.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoappkmm.presentation.CreateNoteScreenViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateNoteScreen(
    viewModel: CreateNoteScreenViewModel = getViewModel(),
    navController: NavController
) {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    viewModel.createNote(title, text)
                },
                modifier = Modifier
                    .fillMaxWidth().padding(16.dp)
                    .height(48.dp)
            ) {
                Text("Create")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Create a note")
            Spacer(modifier = Modifier.height(48.dp))
            OutlinedTextField(
                value = title,
                singleLine = true,
                label = { Text(text = "Title") },
                onValueChange = {
                    title = it
                }
            )
            Spacer(modifier = Modifier.height(48.dp))
            OutlinedTextField(
                value = text,
                singleLine = true,
                label = { Text(text = "Text") },
                onValueChange = {
                    text = it
                }
            )
        }
    }
}