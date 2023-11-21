package com.example.todoappkmm.utils

import com.example.todoappkmm.HomeScreenViewModel
import com.example.todoappkmm.di.getSharedModules
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.component.get
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(getSharedModules())
    }
}

class KoinHelper: KoinComponent {
    fun getHomeScreenViewModel() = get<HomeScreenViewModel>()
}