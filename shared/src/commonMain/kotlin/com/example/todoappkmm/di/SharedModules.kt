package com.example.todoappkmm.di

import com.example.todoappkmm.utils.provideDispatcher
import org.koin.dsl.module


private val utilityModule = module {
    factory { provideDispatcher() }
}

private val sharedModules = listOf(utilityModule, getViewModelByPlatform())

fun getSharedModules() = sharedModules