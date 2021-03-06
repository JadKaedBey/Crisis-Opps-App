package com.example.crisisopp.logIn.datasource

import com.example.crisisopp.logIn.models.LoggedInUser
import com.example.crisisopp.logIn.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class LoginDataSource {
    private var auth: FirebaseAuth = Firebase.auth

    private val usersCollectionRef = Firebase.firestore

    suspend fun login(email: String, password: String): LoggedInUser {
        try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            return LoggedInUser(result.user.uid, result.user.email)
        } catch (e: Exception) {
            throw e
        }
    }
    
    suspend fun fetchToken(): String {
        try {
            val instanceIdResult = FirebaseInstanceId.getInstance().instanceId.await()
            return instanceIdResult.token
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    suspend fun updateUserInfo(user: User) {
        user.userId?.let {
            getUserDocument(it)?.apply {
                reference.set(user).await()
            } ?: addUserInfo(user)
        }
    }

    suspend fun addUserInfo(user: User) {
        usersCollectionRef.collection("users").add(user).await()
    }

    suspend fun getUserInfo(userId: String): User? {
        getUserDocument(userId)?.let {
            return it.toObject<User>()
        } ?: return null
    }


    private suspend fun getUserDocument(userId: String): DocumentSnapshot? {
        val query =
            usersCollectionRef.collection("users").whereEqualTo("userId", userId).get().await()
        return if (query.isEmpty) {
            null
            //should add the user instead of returning null
        } else {
            query.documents.first()
        }
    }
}
