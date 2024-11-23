package com.example.classes.class_10.repository

import com.example.classes.class_10.model.UserRequest
import com.example.classes.class_10.model.UserResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException


class LoginRepository {
    private val firebase_auth = FirebaseAuth.getInstance()


    fun register_user(user_request: UserRequest, user_response: (UserResponse) -> Unit) {
        try {
            firebase_auth.createUserWithEmailAndPassword(user_request.email, user_request.password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        val email = task.result?.user?.email
                        user_response(
                            UserResponse(
                                email = email,
                                is_registered = true,
                                message = "Successful register"
                            )
                        )
                    } else {
                        val error = task.exception
                        if (error is FirebaseAuthUserCollisionException) {
                            user_response(
                                UserResponse(
                                    is_registered = false,
                                    message = "The user already exists"
                                )
                            )
                        } else {
                            user_response(
                                UserResponse(
                                    is_registered = false,
                                    message = "Error in registration"
                                )
                            )
                        }
                    }
                }
        } catch (e: Exception) {
            user_response(
                UserResponse(
                    is_registered = false,
                    message = e.message ?: "Unknown error"
                )
            )
        }
    }
}