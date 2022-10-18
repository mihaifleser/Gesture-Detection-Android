package com.example.bachelor_app.ui.model

import androidx.lifecycle.LiveData
import com.example.bachelor_app.util.SingleLiveEvent

interface INetworkViewModel<T> {
    val progress: LiveData<Boolean>
    val error: SingleLiveEvent<RuntimeException>
    val update: SingleLiveEvent<T>
}