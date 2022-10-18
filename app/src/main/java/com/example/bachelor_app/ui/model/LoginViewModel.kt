package com.example.bachelor_app.ui.model

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bachelor_app.managers.IFirebaseManager
import com.example.bachelor_app.util.SingleLiveEvent
import io.reactivex.rxkotlin.subscribeBy

interface ILoginViewModel : INetworkViewModel<String> {
    val email: ObservableField<String>
    val password: ObservableField<String>
    val repeatPassword: ObservableField<String>
    val loginVisible: LiveData<Boolean>
    fun onLogin()
    fun onSignUp()
}

class LoginViewModel(isLogin: Boolean, private val firebaseManager: IFirebaseManager) : ViewModel(), ILoginViewModel {

    override val update: SingleLiveEvent<String> by lazy { SingleLiveEvent() }

    override val progress: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent() }

    override val error: SingleLiveEvent<RuntimeException> by lazy { SingleLiveEvent() }

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
        email.get()?.let { email ->
            password.get()?.let { password ->
                firebaseManager.createUserWithEmailAndPassword(email, password).subscribeBy(onComplete = {
                    update.postValue(SIGN_UP)
                }, onError = {
                    println(it.message)
                })
            }
        }

    }

    companion object {
        const val SIGN_UP = "sign up done."
    }

}

class LoginViewModelFactory(private val isLogin: Boolean, private val firebaseManager: IFirebaseManager) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(isLogin, firebaseManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}