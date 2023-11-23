package com.example.todoappkmm.utils

import com.example.todoappkmm.presentation.HomeScreenViewModel
import com.example.todoappkmm.di.getSharedModules
import com.example.todoappkmm.presentation.CreateNoteScreenViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.component.get

fun initKoin() {
    startKoin {
        modules(getSharedModules())
    }
}

class KoinHelper: KoinComponent {
    fun getHomeScreenViewModel() = get<HomeScreenViewModel>()
    fun getCreateNoteScreenViewModel() = get<CreateNoteScreenViewModel>()
}