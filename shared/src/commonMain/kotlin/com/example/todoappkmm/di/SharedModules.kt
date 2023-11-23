package com.example.todoappkmm.di

import FirebaseRepository
import com.example.todoappkmm.utils.provideDispatcher
import org.koin.dsl.module
import usecases.CreateNoteUseCase
import usecases.GetNotesUseCase

private val dataModule = module {
    single { FirebaseRepository() }
    factory { GetNotesUseCase() }
    factory { CreateNoteUseCase() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val sharedModules = listOf(dataModule, utilityModule, getViewModelByPlatform())

fun getSharedModules() = sharedModules