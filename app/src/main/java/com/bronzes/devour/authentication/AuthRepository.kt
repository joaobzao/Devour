package com.bronzes.devour.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class AuthRepository @Inject constructor() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential?): LiveData<User>? {
        val authenticatedUserMutableLiveData = MutableLiveData<User>()
        firebaseAuth.signInWithCredential(googleAuthCredential!!).addOnCompleteListener { authTask: Task<AuthResult> ->
            if (authTask.isSuccessful) {
                //val isNewUser = authTask.result!!.additionalUserInfo!!.isNewUser
                val firebaseUser = firebaseAuth.currentUser
                if (firebaseUser != null) {
                    val uid = firebaseUser.uid
                    val name = firebaseUser.displayName
                    val email = firebaseUser.email
                    val user = User(uid, name!!, email!!)
                    authenticatedUserMutableLiveData.value = user
                }
            } else {
                println(authTask.exception!!.message)
            }
        }
        return authenticatedUserMutableLiveData
    }
}