package com.example.lab1.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    var textValue by mutableStateOf("Початковий текст Профілю")
        private set

    fun updateText() {
        textValue = "Текст змінено через у Профілі!"
    }
}