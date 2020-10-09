package com.bronzes.devour.authentication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val uid: String,
    val name: String,
    val email: String,
    val isNew: Boolean,
    val isCreated: Boolean,
    val isAuthenticated: Boolean
): Parcelable