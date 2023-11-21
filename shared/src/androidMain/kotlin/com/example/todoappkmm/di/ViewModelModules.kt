package com.example.todoappkmm.di

import com.example.todoappkmm.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun getViewModelByPlatform() = module {
    viewModel {
        HomeScreenViewModel()
    }
}
