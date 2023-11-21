package com.example.todoappkmm.di

import com.example.todoappkmm.HomeScreenViewModel
import org.koin.dsl.module
actual fun getViewModelByPlatform() = module {
    single {
        HomeScreenViewModel()
    }
}