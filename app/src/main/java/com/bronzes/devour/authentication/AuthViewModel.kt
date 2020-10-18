package com.bronzes.devour.authentication

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthCredential

class AuthViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    var authenticatedUserLiveData: LiveData<User>? = null

    fun signInWithGoogle(googleAuthCredential: AuthCredential) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential)
    }
}