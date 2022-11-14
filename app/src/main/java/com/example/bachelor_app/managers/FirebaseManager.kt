package com.example.bachelor_app.managers

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

interface IFirebaseManager {
    fun isLogged(): Boolean
    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<AuthResult>
    suspend fun createUserWithEmailAndPassword(email: String, password: String): Result<AuthResult>
}

class FirebaseManager : IFirebaseManager {

    override fun isLogged(): Boolean = FirebaseAuth.getInstance().currentUser != null

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Result<AuthResult> {
        return try {
            Result.success(Firebase.auth.signInWithEmailAndPassword(email, password).await())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createUserWithEmailAndPassword(email: String, password: String): Result<AuthResult> {
        return try {
            Result.success(Firebase.auth.createUserWithEmailAndPassword(email, password).await())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}