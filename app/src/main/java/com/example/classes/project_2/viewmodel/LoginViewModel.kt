package com.example.classes.project_2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.classes.project_2.model.UserRequest
import com.example.classes.project_2.model.UserResponse
import com.example.classes.project_2.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
	private val repository: LoginRepository

): ViewModel() {
	private val _is_registered = MutableLiveData <UserResponse>()
	val is_registered: LiveData <UserResponse> = _is_registered


	fun register_user(user_request: UserRequest) {
		repository.register_user(user_request) { userResponse ->
			_is_registered.value = userResponse
		}
	}

	fun login_user(user_request: UserRequest, is_logged_in: (Boolean) -> Unit) {
		repository.login_user(user_request, is_logged_in)
	}

	fun logout_user() {
		repository.logout_user()
	}

	fun session(email: String?, callback: (Boolean) -> Unit) {
		if (email != null)
			callback(true)
		else
			callback(false)
	}
}