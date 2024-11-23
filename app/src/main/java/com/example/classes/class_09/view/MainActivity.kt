package com.example.classes.class_09.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.classes.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_08)
    }
}