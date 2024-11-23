package com.example.classes.project_2.repository

import com.example.classes.project_2.model.UserRequest
import com.example.classes.project_2.model.UserResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import javax.inject.Inject


class LoginRepository @Inject constructor(
	private val firebaseAuth: FirebaseAuth
) {
	fun registerUser(userRequest: UserRequest, callback: (UserResponse) -> Unit) {
		try {
			firebaseAuth.createUserWithEmailAndPassword(userRequest.email, userRequest.password)
				.addOnCompleteListener { task ->
					lateinit var userResponse: UserResponse

					if (task.isSuccessful) {
						val email = task.result?.user?.email
						userResponse = UserResponse(
								email = email,
								is_registered = true,
								message = "Successful register"
							)
					} else {
						val error = task.exception
						userResponse = if (error is FirebaseAuthUserCollisionException) {
									UserResponse(
										is_registered = false,
										message = "The user already exists"
									)
								} else {
									UserResponse(
										is_registered = false,
										message = "Error in registration"
									)
								}
					}
					callback(userResponse)
				}
		} catch (e: Exception) {
			callback(
				UserResponse(
					is_registered = false,
					message = e.message ?: "Unknown error"
				)
			)
		}
	}

	fun loginUser(userRequest: UserRequest, callback: (Boolean) -> Unit) {
		val email = userRequest.email
		val password = userRequest.password

		if (email.isNotEmpty() && password.isNotEmpty()) {
			firebaseAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener {
					if (it.isSuccessful)
						callback(true)
					else
						callback(false)
				}
		} else
			callback(false)
	}

	fun logoutUser() {
		firebaseAuth.signOut()
	}
}