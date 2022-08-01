package com.example.bachelor_app.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bachelor_app.MainActivity.Companion.IS_LOGIN
import com.example.bachelor_app.R
import com.example.bachelor_app.databinding.ActivityLoginBinding
import com.example.bachelor_app.ui.model.ILoginViewModel
import com.example.bachelor_app.ui.model.LoginViewModel
import com.example.bachelor_app.ui.model.LoginViewModelFactory

class LoginScreen : AppCompatActivity() {

    private var isLogin = false

    private val viewModel: ILoginViewModel by lazy {
        val factory = LoginViewModelFactory(isLogin)
        ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isLogin = intent.getBooleanExtra(IS_LOGIN, false)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_login
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}