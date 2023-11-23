package com.example.todoappkmm.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoappkmm.presentation.HomeScreenViewModel
import com.example.todoappkmm.R
import com.example.todoappkmm.SharedResources
import com.example.todoappkmm.android.navigation.Screen
import com.example.todoappkmm.model.Note
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = getViewModel(), navController: NavController){
    var list by remember { mutableStateOf(listOf<Note>()) }
    viewModel.listOfNotes.addObserver {
        list = it
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        floatingActionButton = {
            // Floating Action Button
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.CreateNoteScreen.route)
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            if (!list.isNullOrEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    item {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(id = SharedResources.strings.notes),
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                modifier = Modifier.size(48.dp),
                                painter = painterResource(id = R.drawable.note),
                                contentDescription = "note"
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    items(list) {
                        NoteItem(note = it)
                    }
                }
            }
        }
    }

}
