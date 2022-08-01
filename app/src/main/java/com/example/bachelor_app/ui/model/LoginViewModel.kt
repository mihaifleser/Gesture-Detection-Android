package com.example.bachelor_app.ui.model

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

interface ILoginViewModel {
    val email: ObservableField<String>
    val password: ObservableField<String>
    val repeatPassword: ObservableField<String>
    val loginVisible: LiveData<Boolean>
    fun onLogin()
    fun onSignUp()
}

class LoginViewModel(isLogin: Boolean) : ViewModel(), ILoginViewModel {

    override val email: ObservableField<String> by lazy { ObservableField<String>() }

    override val password: ObservableField<String> by lazy { ObservableField<String>() }

    override val repeatPassword: ObservableField<String> by lazy { ObservableField<String>() }

    override val loginVisible: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    init {
        loginVisible.postValue(isLogin)
    }

    override fun onLogin() {
        println(email.get() + " " + password.get())
    }

    override fun onSignUp() {
        println(email.get() + " " + password.get())
    }
}

class LoginViewModelFactory(private val isLogin: Boolean) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(isLogin) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}