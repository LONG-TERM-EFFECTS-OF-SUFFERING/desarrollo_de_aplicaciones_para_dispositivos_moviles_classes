package com.example.classes.class_10.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.classes.R
import com.example.classes.databinding.Class10LoginBinding
import com.example.classes.class_10.model.UserRequest
import com.example.classes.class_10.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
	private lateinit var binding: Class10LoginBinding
	private val loginViewModel: LoginViewModel by viewModels()
	private lateinit var sharedPreferences: SharedPreferences


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this,R.layout.class_10_login)

		sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
		sesion()
		setup()
		viewModelObservers()
	}
	private fun viewModelObservers() {
		observerIsRegister()
	}
	private fun observerIsRegister() {
		loginViewModel.is_registered.observe(this) { userResponse ->
			if (userResponse.is_registered) {
				Toast.makeText(this, userResponse.message, Toast.LENGTH_SHORT).show()
				sharedPreferences.edit().putString("email",userResponse.email).apply()
				goToHome()
			} else {
				Toast.makeText(this, userResponse.message, Toast.LENGTH_SHORT).show()
			}
		}
	}

	private fun setup() {
		binding.tvRegister.setOnClickListener {
			registerUser()
		}

		binding.btnLogin.setOnClickListener {
			loginUser()
		}
	}

	private fun registerUser() {
		val email = binding.etEmail.text.toString()
		val pass = binding.etPass.text.toString()
		val userRequest = UserRequest(email, pass)

		if (email.isNotEmpty() && pass.isNotEmpty()) {
			loginViewModel.register_user(userRequest)
		} else {
			Toast.makeText(this, "Campos Vacíos", Toast.LENGTH_SHORT).show()
		}

	}
	private fun goToHome(){
		val intent = Intent (this, MainActivity::class.java)
		startActivity(intent)
		finish()
	}
	private fun loginUser(){
		val email = binding.etEmail.text.toString()
		val pass = binding.etPass.text.toString()
		loginViewModel.login_user(email,pass){ isLogin ->
			if (isLogin){
				sharedPreferences.edit().putString("email",email).apply()
				goToHome()
			}else {
				Toast.makeText(this, "Login incorrecto", Toast.LENGTH_SHORT).show()
			}
		}
	}
	private fun sesion(){
		val email = sharedPreferences.getString("email",null)
		loginViewModel.session(email){ isEnableView ->
			if (isEnableView){
				binding.clContenedor.visibility = View.INVISIBLE
				goToHome()
			}
		}
	}
}