package com.geisyanne.taskapp.util

import com.geisyanne.taskapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseHelper {

    companion object {
        fun getDatabase() = Firebase.database.reference

        fun getAuth() = FirebaseAuth.getInstance()

        fun getIdUser() = getAuth().currentUser?.uid ?: ""

        fun isAuthenticated() = getAuth().currentUser != null

        fun validError(error: String): Int {
            return when {
                error.contains("The supplied auth credential is incorrect, malformed or has expired") -> {
                    R.string.invalid_credentials
                }
                error.contains("The email address is badly formatted") -> {
                    R.string.invalid_email_register
                }
                error.contains("The email address is already in use by another account") -> {
                    R.string.email_in_use
                }
                error.contains("Password should be at least 6 characters") -> {
                    R.string.strong_password_register
                }
                else -> {
                    R.string.error_generic
                }
            }
        }


    }

}