package com.example.todoappkmm

import com.example.todoappkmm.Platform
import com.example.todoappkmm.getPlatform

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}