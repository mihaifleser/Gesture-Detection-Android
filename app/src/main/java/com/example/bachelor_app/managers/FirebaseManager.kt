package com.example.bachelor_app.managers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.reactivex.Completable
import io.reactivex.subjects.CompletableSubject
import java.util.concurrent.CancellationException

interface IFirebaseManager {
    fun isLogged(): Boolean
    fun signInWithEmailAndPassword(email: String, password: String): Completable
    fun createUserWithEmailAndPassword(email: String, password: String): Completable
}

class FirebaseManager : IFirebaseManager {

    override fun isLogged(): Boolean = FirebaseAuth.getInstance().currentUser != null

    override fun signInWithEmailAndPassword(email: String, password: String): Completable {
        val result = CompletableSubject.create()
        return result.doOnSubscribe {
            Firebase.auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                result.onComplete()
            }.addOnFailureListener {
                result.onError(it)
            }.addOnCanceledListener {
                result.onError(CancellationException())
            }
        }
    }

    override fun createUserWithEmailAndPassword(email: String, password: String): Completable {
        val result = CompletableSubject.create()
        return result.doOnSubscribe {
            Firebase.auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                result.onComplete()
            }.addOnFailureListener {
                result.onError(it)
            }.addOnCanceledListener {
                result.onError(CancellationException())
            }
        }
    }

}