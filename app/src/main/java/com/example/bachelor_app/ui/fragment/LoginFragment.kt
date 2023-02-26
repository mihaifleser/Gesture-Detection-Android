package com.example.bachelor_app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bachelor_app.BachelorApplication
import com.example.bachelor_app.R
import com.example.bachelor_app.managers.IFirebaseManager
import com.example.bachelor_app.ui.model.ILoginViewModel
import com.example.bachelor_app.ui.model.LoginMessage
import com.example.bachelor_app.ui.model.LoginViewModel
import com.example.bachelor_app.ui.model.LoginViewModelFactory
import com.example.bachelor_app.ui.screen.LoginScreen
import com.example.bachelor_app.ui.theme.BachelorAppTheme
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    internal lateinit var firebaseManager: IFirebaseManager

    private val viewModel: ILoginViewModel by lazy {
        val factory = LoginViewModelFactory(firebaseManager)
        ViewModelProvider(this, factory)[LoginViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as BachelorApplication).applicationComponent.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return ComposeView(requireContext()).apply {
            setContent {
                BachelorAppTheme {
                    LoginScreen(
                        onSignUp = viewModel.onSignUp,
                        onLogIn = viewModel.onLogin
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.update.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.second, Toast.LENGTH_SHORT).show()
            if (it.first == LoginMessage.LOGIN) {
                view.findNavController().navigate(R.id.action_loginFragment_to_sensorsFragment)
            }
        }
    }
}