package com.example.todoappkmm.android.navigation

sealed class Screen (val route: String) {
    object HomeScreen : Screen("home_screen")
    object CreateNoteScreen : Screen("create_note_screen")
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }
}