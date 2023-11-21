package com.example.todoappkmm.android

import android.app.Application
import com.example.todoappkmm.di.getSharedModules
import org.koin.core.context.startKoin

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(modules = getSharedModules())
        }
    }
}