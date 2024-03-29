package com.example.bachelor_app.ui.model

import android.content.res.AssetManager
import android.media.MediaPlayer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bachelor_app.util.SingleLiveEvent
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent

interface ISensorsViewModel {
    val toast: SingleLiveEvent<String>
    val currentSong: MutableState<String>
    val tracker: MutableState<String>
    val gestures: MutableState<List<String>>
    val play: MutableState<Boolean>
    fun next()
    fun previous()
    fun stopResume()
}

class SensorsViewModel(private val messageClient: MessageClient, private val assetManager: AssetManager) :
    ISensorsViewModel, MessageClient.OnMessageReceivedListener, ViewModel() {

    override val toast: SingleLiveEvent<String> by lazy { SingleLiveEvent() }

    override val currentSong: MutableState<String> by lazy { mutableStateOf("") }

    override val tracker: MutableState<String> by lazy { mutableStateOf("") }

    override val gestures: MutableState<List<String>> by lazy { mutableStateOf(emptyList()) }

    override val play: MutableState<Boolean> by lazy { mutableStateOf(true) }

    private val mediaPlayer = MediaPlayer()

    private val songs = assetManager.list("")?.toList()?.filter { it.contains(".mp3") } ?: emptyList()

    private var index = 0
        set(value) {
            field = value
            currentSong.value = songs[value]
            tracker.value = "${value + 1}/${songs.size}"
            val afd = assetManager.openFd(songs[value])
            mediaPlayer.reset()
            mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            mediaPlayer.prepare()
            mediaPlayer.start()
            play.value = true
        }

    init {
        messageClient.addListener(this)
        index = 0
    }

    override fun onCleared() {
        messageClient.removeListener(this)
        mediaPlayer.pause()
        mediaPlayer.release()
    }

    override fun onMessageReceived(p0: MessageEvent) {
        val message = String(p0.data)
        toast.postValue(message)

        val gesture = MeasurementType.fromString(message)
        gesture?.let { gestures.value = gestures.value.plus(it.description) }
        when (gesture) {
            MeasurementType.UP_DOWN -> {
                next()
            }

            MeasurementType.LEFT_RIGHT -> {
                previous()
            }

            MeasurementType.ROTATE -> {
                stopResume()
            }

            else -> println("Incorrect message")
        }
    }

    override fun next() {
        if (index >= songs.size - 1) {
            index = 0
        } else {
            index++
        }
    }

    override fun previous() {
        if (index > 0) {
            index--
        } else {
            index = 0
        }
    }

    override fun stopResume() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            play.value = false
        } else {
            mediaPlayer.start()
            play.value = true
        }
    }
}

enum class MeasurementType(val description: String, val type: Int) {
    UP_DOWN("Up and down", 0),
    LEFT_RIGHT("Left and right", 1),
    ROTATE("Rotate wrist", 2);

    companion object {
        fun fromString(name: String): MeasurementType? {
            values().forEach {
                if (it.name == name) {
                    return it
                }
            }
            return null
        }
    }
}

class SensorsViewModelFactory(private val messageClient: MessageClient, private val assetManager: AssetManager) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SensorsViewModel::class.java)) {
            return SensorsViewModel(messageClient, assetManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}