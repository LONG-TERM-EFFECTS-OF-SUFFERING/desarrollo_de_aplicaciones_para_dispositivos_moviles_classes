package com.example.classes.class_10.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.classes.R
import com.example.classes.databinding.Class10Binding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: Class10Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.class_10)
    }
}
