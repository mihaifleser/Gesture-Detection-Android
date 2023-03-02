package com.example.bachelor_app.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bachelor_app.util.SingleLiveEvent
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent

interface ISensorsViewModel {
    val toast: SingleLiveEvent<Boolean>
}

class SensorsViewModel(private val messageClient: MessageClient) : ISensorsViewModel, MessageClient.OnMessageReceivedListener, ViewModel() {

    override val toast: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent() }

    init {
        messageClient.addListener(this)
    }

    override fun onCleared() {
        messageClient.removeListener(this)
    }

    override fun onMessageReceived(p0: MessageEvent) {
        toast.postValue(true)
        println("Received " + String(p0.data))
    }

}

class SensorsViewModelFactory(private val messageClient: MessageClient) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SensorsViewModel::class.java)) {
            return SensorsViewModel(messageClient) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}