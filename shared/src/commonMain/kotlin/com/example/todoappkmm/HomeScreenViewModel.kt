package com.example.todoappkmm

import FirebaseRepository
import com.example.todoappkmm.model.Note
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch


class HomeScreenViewModel : ViewModel() {
    val listOfNotes: MutableLiveData<List<Note>> = MutableLiveData(listOf())

    init {
        viewModelScope.launch {
            listOfNotes.value = FirebaseRepository().getNotes()
        }
    }

    fun potatoes() {

    }
}