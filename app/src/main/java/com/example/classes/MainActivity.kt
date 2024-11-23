package com.example.classes

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.classes.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initialize_spinner()
        go()
    }

    private fun initialize_spinner() {
        val classes : Array <String> = resources.getStringArray(R.array.classes_list)
        val adapter : ArrayAdapter <String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, classes)
        binding.spinnerClasses.adapter = adapter
    }

    private fun go() {
        binding.btnGo.setOnClickListener {
            val selected_class = binding.spinnerClasses.selectedItem.toString()

            if (selected_class.isEmpty()) {
                val toast = Toast.makeText(this, "Please select a class", Toast.LENGTH_SHORT)
                toast.show()
            }

            when (selected_class) {
                "02 - BMI calculator" -> {
                    val intent = Intent(this, Class_02::class.java)
                    startActivity(intent)
                }
                "03 - Grade calculator" -> {
                    val intent = Intent(this, com.example.classes.class_03.MainActivity::class.java)
                    startActivity(intent)
                }
                "04 - Constraint layout" -> {
                    val intent = Intent(this, Class_04::class.java)
                    startActivity(intent)
                }
                "05 - Card manager" -> {
                    val intent = Intent(this, com.example.classes.class_05.MainActivity::class.java)
                    startActivity(intent)
                }
                "06 - Grade calculator with fragments" -> {
                    val intent = Intent(this, com.example.classes.class_06.MainActivity::class.java)
                    startActivity(intent)
                }
                "07 - Class 7" -> {
                    val intent = Intent(this, com.example.classes.class_07.MainActivity::class.java)
                    startActivity(intent)
                }
                "08 - Inventory manager" -> {
                    val intent = Intent(this, com.example.classes.class_08.view.MainActivity::class.java)
                    startActivity(intent)
                }
                "Project 1 - Bottle flip" -> {
                    val intent = Intent(this, com.example.classes.project_1.MainActivity::class.java)
                    startActivity(intent)
                }
                "09 - Inventory manager with dagger hilt" -> {
                    val intent = Intent(this, com.example.classes.class_09.view.MainActivity::class.java)
                    startActivity(intent)
                }
                "10 - Inventory manager with login" -> {
                    val intent = Intent(this, com.example.classes.class_10.view.LoginActivity::class.java)
                    startActivity(intent)
                }
                "Project 2 - Bottle flip login" -> {
                    val intent = Intent(this, com.example.classes.project_2.view.LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
