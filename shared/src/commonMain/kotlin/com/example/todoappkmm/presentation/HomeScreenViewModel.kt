package com.example.todoappkmm.presentation

import com.example.todoappkmm.model.Note
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import usecases.CreateNoteUseCase
import usecases.GetNotesUseCase

class HomeScreenViewModel(
    private val getNotesUseCase: GetNotesUseCase,
) : ViewModel() {
    val listOfNotes: MutableLiveData<List<Note>> = MutableLiveData(listOf())

    init {
        viewModelScope.launch {
            listOfNotes.value = getNotesUseCase.execute()
        }
    }
}