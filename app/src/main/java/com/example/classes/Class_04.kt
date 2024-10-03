package com.example.classes;

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.classes.databinding.Class04Binding


class Class_04 : AppCompatActivity() {
	private lateinit var binding: Class04Binding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.class_04)
		binding = DataBindingUtil.setContentView(this, R.layout.class_04)
	}
}
