package com.example.classes.project_2.repository

import com.example.classes.project_2.model.UserRequest
import com.example.classes.project_2.model.UserResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import javax.inject.Inject


class LoginRepository @Inject constructor(
	private val firebaseAuth: FirebaseAuth
) {
	fun register_user(user_request: UserRequest, callback: (UserResponse) -> Unit) {
		try {
			firebaseAuth.createUserWithEmailAndPassword(user_request.email, user_request.password)
				.addOnCompleteListener { task ->
					lateinit var userResponse: UserResponse

					if (task.isSuccessful) {
						val email = task.result?.user?.email
						userResponse = UserResponse(
								email = email,
								isRegistered = true,
								message = "Successful register"
							)
					} else {
						val error = task.exception
						userResponse = if (error is FirebaseAuthUserCollisionException) {
									UserResponse(
										isRegistered = false,
										message = "The user already exists"
									)
								} else {
									UserResponse(
										isRegistered = false,
										message = "Error in registration"
									)
								}
					}
					callback(userResponse)
				}
		} catch (e: Exception) {
			callback(
				UserResponse(
					isRegistered = false,
					message = e.message ?: "Unknown error"
				)
			)
		}
	}

	fun login_user(user_request: UserRequest, callback: (Boolean) -> Unit) {
		val email = user_request.email
		val password = user_request.password

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

	fun logout_user() {
		firebaseAuth.signOut()
	}
}