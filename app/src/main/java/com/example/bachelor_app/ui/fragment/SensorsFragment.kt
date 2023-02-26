package com.example.bachelor_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import com.example.bachelor_app.ui.model.ISensorsViewModel
import com.example.bachelor_app.ui.model.SensorsViewModel
import com.example.bachelor_app.ui.model.SensorsViewModelFactory
import com.example.bachelor_app.ui.screen.SensorsScreen
import com.example.bachelor_app.ui.theme.BachelorAppTheme
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.Wearable

class SensorsFragment : BaseFragment(), MessageClient.OnMessageReceivedListener {

    private val viewModel: ISensorsViewModel by lazy {
        val factory = SensorsViewModelFactory()
        ViewModelProvider(this, factory)[SensorsViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return ComposeView(requireContext()).apply {
            setContent {
                BachelorAppTheme {
                    SensorsScreen(

                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Wearable.getMessageClient(requireActivity()).addListener(this)
    }

    override fun onPause() {
        super.onPause()
        Wearable.getMessageClient(requireActivity()).removeListener(this)
    }

    override fun onMessageReceived(p0: MessageEvent) {
        Toast.makeText(context, "Message Received", Toast.LENGTH_SHORT).show()
        println("Received " + String(p0.data))
    }

}