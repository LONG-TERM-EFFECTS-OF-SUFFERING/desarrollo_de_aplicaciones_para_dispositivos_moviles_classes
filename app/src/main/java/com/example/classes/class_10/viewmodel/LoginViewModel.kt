package com.example.classes.class_10.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.classes.class_10.model.UserRequest
import com.example.classes.class_10.model.UserResponse
import com.example.classes.class_10.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth


class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()
    private val _is_registered = MutableLiveData<UserResponse>()
    val is_registered: LiveData<UserResponse> = _is_registered


    fun register_user(user_request: UserRequest) {
        repository.register_user(user_request) { userResponse ->
            _is_registered.value = userResponse
        }
    }

    fun login_user(email: String, pass: String, isLoggedIn: (Boolean) -> Unit) {
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful)
                        isLoggedIn(true)
                    else
                        isLoggedIn(false)
                }
        } else
            isLoggedIn(false)
    }

    fun session(email: String?, isEnableView: (Boolean) -> Unit) {
        if (email != null)
            isEnableView(true)
        else
            isEnableView(false)
    }
}