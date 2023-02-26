package com.example.bachelor_app.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

interface ISensorsViewModel {

}

class SensorsViewModel : ISensorsViewModel, ViewModel() {

}

class SensorsViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SensorsViewModel::class.java)) {
            return SensorsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}