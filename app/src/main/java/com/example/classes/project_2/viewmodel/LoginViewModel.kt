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
	private val _isRegistered = MutableLiveData<UserResponse>()
	val isRegistered: LiveData<UserResponse> = _isRegistered


	fun registerUser(userRequest: UserRequest) {
		repository.registerUser(userRequest) { userResponse ->
			_isRegistered.value = userResponse
		}
	}

	fun loginUser(userRequest: UserRequest, isLoggedIn: (Boolean) -> Unit) {
		repository.loginUser(userRequest, isLoggedIn)
	}

	fun session(email: String?, callback: (Boolean) -> Unit) {
		if (email != null)
			callback(true)
		else
			callback(false)
	}
}