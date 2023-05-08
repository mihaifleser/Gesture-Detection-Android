package com.example.bachelor_app.ui.fragment

import android.content.Context
import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import com.example.bachelor_app.BachelorApplication
import com.example.bachelor_app.ui.model.ISensorsViewModel
import com.example.bachelor_app.ui.model.SensorsViewModel
import com.example.bachelor_app.ui.model.SensorsViewModelFactory
import com.example.bachelor_app.ui.screen.SensorsScreen
import com.example.bachelor_app.ui.theme.BachelorAppTheme
import com.google.android.gms.wearable.MessageClient
import javax.inject.Inject

class SensorsFragment : BaseFragment() {

    @Inject
    internal lateinit var messageClient: MessageClient

    private lateinit var assetManager: AssetManager

    private val viewModel: ISensorsViewModel by lazy {
        val factory = SensorsViewModelFactory(messageClient, assetManager)
        ViewModelProvider(this, factory)[SensorsViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as BachelorApplication).applicationComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        assetManager = requireContext().assets

        return ComposeView(requireContext()).apply {
            setContent {
                BachelorAppTheme {
                    SensorsScreen(viewModel)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.toast.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

}