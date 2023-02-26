package com.example.bachelor_app.ui.model

import androidx.lifecycle.*
import com.example.bachelor_app.managers.IFirebaseManager
import com.example.bachelor_app.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface ILoginViewModel : INetworkViewModel<Pair<LoginMessage, String>> {
    val onLogin: (String, String) -> Unit
    val onSignUp: (String, String) -> Unit
}

class LoginViewModel(private val firebaseManager: IFirebaseManager) : ViewModel(), ILoginViewModel {

    override val update: SingleLiveEvent<Pair<LoginMessage, String>> by lazy { SingleLiveEvent() }

    override val progress: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent() }

    override val error: SingleLiveEvent<RuntimeException> by lazy { SingleLiveEvent() }

    override val onLogin: (String, String) -> Unit = { email, password ->
        println("$email $password")
        viewModelScope.launch(Dispatchers.IO) {
            firebaseManager.signInWithEmailAndPassword(email, password).onSuccess {
                update.postValue(LoginMessage.LOGIN to LOG_IN)
            }.onFailure {
                update.postValue(LoginMessage.ERROR to it.message.toString())
            }
        }
    }

    override val onSignUp: (String, String) -> Unit = { email, password ->
        println("$email $password")
        viewModelScope.launch(Dispatchers.IO) {
            firebaseManager.createUserWithEmailAndPassword(email, password).onSuccess {
                update.postValue(LoginMessage.SIGNUP to SIGN_UP)
            }.onFailure {
                update.postValue(LoginMessage.ERROR to it.message.toString())
            }
        }
    }

    companion object {
        const val SIGN_UP = "Sign up done!"
        const val LOG_IN = "Log in done!"
    }

}

class LoginViewModelFactory(private val firebaseManager: IFirebaseManager) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(firebaseManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

enum class LoginMessage { LOGIN, SIGNUP, ERROR }