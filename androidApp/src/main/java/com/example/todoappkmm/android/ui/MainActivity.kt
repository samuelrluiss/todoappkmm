package com.example.todoappkmm.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoappkmm.Strings
import com.example.todoappkmm.android.MyApplicationTheme
import com.example.todoappkmm.android.navigation.Screen
import com.example.todoappkmm.model.Note
import com.google.firebase.Firebase
import com.google.firebase.initialize
import dev.icerock.moko.resources.StringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Firebase.initialize(this) // This line

        setContent {
            val navController = rememberNavController()

            MyApplicationTheme {
                NavHost(navController = navController, startDestination = Screen.HomeScreen.route ) {
                    composable(route = Screen.HomeScreen.route) {
                        HomeScreen(navController = navController)
                    }
                    composable(route = Screen.CreateNoteScreen.route) {
                        CreateNoteScreen(navController = navController)
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
        NoteItem(note = Note("Title", "Text sdjaskdnaskdnaskdnaskdnas dasndnaskndkasndkasdn ", platform = "iOS"))
    }
}
