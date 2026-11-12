package com.example.lab1.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var textValue by mutableStateOf("Початковий текст")
        private set

    fun updateText() {
        textValue = "Текст змінено через"
    }
}