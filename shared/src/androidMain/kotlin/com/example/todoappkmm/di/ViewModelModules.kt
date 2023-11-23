package com.example.todoappkmm.di

import com.example.todoappkmm.presentation.CreateNoteScreenViewModel
import com.example.todoappkmm.presentation.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun getViewModelByPlatform() = module {
    viewModel {
        HomeScreenViewModel(get())
    }

    viewModel {
        CreateNoteScreenViewModel(get())
    }
}
