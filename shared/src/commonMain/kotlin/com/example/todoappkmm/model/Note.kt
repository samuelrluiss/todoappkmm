package com.example.todoappkmm.model

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val title: String,
    val text: String
)