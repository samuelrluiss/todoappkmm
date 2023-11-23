package com.example.todoappkmm.presentation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import usecases.CreateNoteUseCase

class CreateNoteScreenViewModel(
    private val createNoteUseCase: CreateNoteUseCase
) : ViewModel() {
    fun createNote(title: String, text: String) {
        viewModelScope.launch {
            createNoteUseCase.execute(title, text)
        }
    }
}