package com.example.todoappkmm.di

import com.example.todoappkmm.presentation.CreateNoteScreenViewModel
import com.example.todoappkmm.presentation.HomeScreenViewModel
import org.koin.dsl.module
actual fun getViewModelByPlatform() = module {
    single {
        HomeScreenViewModel(get())
    }
    single {
        CreateNoteScreenViewModel(get())
    }
}