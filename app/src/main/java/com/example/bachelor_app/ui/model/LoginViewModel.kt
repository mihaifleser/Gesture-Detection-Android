package com.example.bachelor_app.ui.model

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

interface ILoginViewModel {
    val email: ObservableField<String>
    val password: ObservableField<String>
    fun onLogin()

}

class LoginViewModel : ViewModel(), ILoginViewModel {

    override val email: ObservableField<String> by lazy { ObservableField<String>() }

    override val password: ObservableField<String> by lazy { ObservableField<String>() }

    override fun onLogin() {
        println(email.get() + " " + password.get())
    }
}