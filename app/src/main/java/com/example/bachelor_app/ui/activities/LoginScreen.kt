package com.example.bachelor_app.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bachelor_app.BachelorApplication
import com.example.bachelor_app.R
import com.example.bachelor_app.databinding.ActivityLoginBinding
import com.example.bachelor_app.managers.IFirebaseManager
import com.example.bachelor_app.ui.activities.StartScreen.Companion.IS_LOGIN
import com.example.bachelor_app.ui.model.ILoginViewModel
import com.example.bachelor_app.ui.model.LoginViewModel
import com.example.bachelor_app.ui.model.LoginViewModelFactory
import javax.inject.Inject

class LoginScreen : AppCompatActivity() {

    private var isLogin = false

    @Inject
    internal lateinit var firebaseManager: IFirebaseManager

    private val viewModel: ILoginViewModel by lazy {
        val factory = LoginViewModelFactory(isLogin, firebaseManager)
        ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as BachelorApplication).applicationComponent.inject(this)

        super.onCreate(savedInstanceState)
        isLogin = intent.getBooleanExtra(IS_LOGIN, false)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_login
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.update.observe(this) { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
    }

}