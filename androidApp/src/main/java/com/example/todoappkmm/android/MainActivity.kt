package com.example.todoappkmm.android

import FirebaseRepository
import Note
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoappkmm.SharedResources
import com.example.todoappkmm.Strings
import com.google.firebase.Firebase
import com.google.firebase.initialize
import dev.icerock.moko.resources.StringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Firebase.initialize(this) // This line
        setContent {
            var list by remember { mutableStateOf(listOf<Note>()) }

            LaunchedEffect(Unit) {
                list = FirebaseRepository().getNotes()
            }

            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.background),
                    floatingActionButton = {
                        // Floating Action Button
                        FloatingActionButton(
                            onClick = {
                                //todo call create Note
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
                                            painter = painterResource(id = com.example.todoappkmm.R.drawable.note),
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
        }
    }
}

@Composable
fun stringResource (id: StringResource, vararg args: Any) : String {
    return Strings(LocalContext.current).get(id, args. toList ())
}

@Composable
fun NoteItem(note: Note) {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = note.title
        )
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = if (note.text.length > 20) note.text.take(20) + "..." else note.text,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        NoteItem(note = Note("Title", "Text sdjaskdnaskdnaskdnaskdnas dasndnaskndkasndkasdn "))
    }
}
