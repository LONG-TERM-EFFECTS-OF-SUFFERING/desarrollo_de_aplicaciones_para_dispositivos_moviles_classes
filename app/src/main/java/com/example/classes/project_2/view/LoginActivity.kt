package com.example.classes.project_2.view

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.classes.R
import com.example.classes.databinding.Project2LoginBinding
import com.example.classes.project_2.model.UserRequest
import com.example.classes.project_2.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {
	private lateinit var binding: Project2LoginBinding
	private val view_model: LoginViewModel by viewModels()
	private lateinit var shared_preferences: SharedPreferences


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this,R.layout.project_2_login)
		shared_preferences = getSharedPreferences("shared", Context.MODE_PRIVATE)

		setup()
		view_model_observers()
		check_session()
	}

	private fun view_model_observers() {
		is_registered_observer()
	}

	private fun is_registered_observer() {
		view_model.is_registered.observe(this) { user_response ->
			if (user_response.isRegistered) {
				Toast.makeText(this, user_response.message, Toast.LENGTH_SHORT).show()
				shared_preferences.edit().putString("email", user_response.email).apply()
				go_home()
			} else {
				Toast.makeText(this, user_response.message, Toast.LENGTH_SHORT).show()
			}
		}
	}

	private fun setup() {
		binding.tvRegister.setOnClickListener {
			register_user()
		}

		binding.btnLogin.setOnClickListener {
			login_user()
		}

		check_buttons()
	}

	private fun register_user() {
		val email = binding.etEmail.text.toString()
		val pass = binding.etPassword.text.toString()
		val userRequest = UserRequest(email, pass)

		if (email.isNotEmpty() && pass.isNotEmpty())
			view_model.register_user(userRequest)
		else
			Toast.makeText(this, "Error: empty fields", Toast.LENGTH_SHORT).show()
	}

	private fun go_home() {
//		val intent = Intent (this, MainActivity::class.java)
//		startActivity(intent)
//		finish()
		Toast.makeText(this, "Successful login", Toast.LENGTH_SHORT).show()
	}

	private fun login_user(){
		val email = binding.etEmail.text.toString()
		val pass = binding.etPassword.text.toString()
		val user_request = UserRequest(email, pass)

		view_model.login_user(user_request){ is_logged_in ->
			if (is_logged_in) {
				shared_preferences.edit().putString("email", email).apply()
				go_home()
			} else
				Toast.makeText(this, "Error: incorrect login", Toast.LENGTH_SHORT).show()
		}
	}

	private fun are_fields_valid() : Boolean {
		val email = binding.etEmail.text.toString()
		val password = binding.etPassword.text.toString()


		if (password.length < 6) {
			binding.tilPassword.error = "Mínimo 6 dígitos"
			binding.tilPassword.boxStrokeColor = ContextCompat.getColor(this@LoginActivity, R.color.red)
			return false
		} else {
			binding.tilPassword.error = null
			return email.isNotEmpty()
		}
	}

	private fun check_buttons() {
		val textWatcher = object : TextWatcher {
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				val areFieldsValid = are_fields_valid()
				binding.btnLogin.isEnabled = areFieldsValid
				binding.tvRegister.isEnabled = areFieldsValid

				if (areFieldsValid) {
					binding.tvRegister.setTypeface(null, Typeface.BOLD)
					binding.btnLogin.setTypeface(null, Typeface.BOLD)

				} else {
					binding.tvRegister.setTypeface(null, Typeface.NORMAL)
					binding.btnLogin.setTypeface(null, Typeface.NORMAL)
				}
			}

			override fun afterTextChanged(s: Editable?) {}
		}

		binding.etEmail.addTextChangedListener(textWatcher)
		binding.etPassword.addTextChangedListener(textWatcher)
	}

	private fun check_session() {
		val email = shared_preferences.getString("email", null)
		view_model.session(email) { isViewEnable ->
			if (isViewEnable) {
//				binding.clContainer.visibility = View.INVISIBLE
				shared_preferences.edit().clear().apply()
				view_model.logout_user()
				go_home()
			}
		}
	}
}
