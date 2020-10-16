package com.bronzes.devour.authentication

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthCredential
import javax.inject.Inject

class AuthViewModel @ViewModelInject constructor(): ViewModel() {

    @Inject lateinit var authRepository: AuthRepository

    var authenticatedUserLiveData: LiveData<User>? = null
    var testUserLiveData: LiveData<User>? = null

    fun signInWithGoogle(googleAuthCredential: AuthCredential?) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential)
    }
}