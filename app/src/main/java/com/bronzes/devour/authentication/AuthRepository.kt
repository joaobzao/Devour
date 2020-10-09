package com.bronzes.devour.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential


class AuthRepository {
    fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential?): LiveData<User>? {
        val authenticatedUserMutableLiveData = MutableLiveData<User>()

        TODO("fill this up")

        return authenticatedUserMutableLiveData
    }

    fun createUserInFirestoreIfNotExists(authenticatedUser: User?): LiveData<User>? {
        TODO("Not yet implemented")
    }
}