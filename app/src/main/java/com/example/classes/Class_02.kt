package com.example.classes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.classes.databinding.Class02Binding


class Class_02 : AppCompatActivity() {
    private lateinit var binding: Class02Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_02)
        binding = DataBindingUtil.setContentView(this, R.layout.class_02)

        result()
        reset()
    }

    private fun result() {
        binding.btnCalculate.setOnClickListener {
            val height_string = binding.etHeight.text.toString()
            val weight_string = binding.etWeight.text.toString()

            if (height_string.isEmpty() || weight_string.isEmpty()) {
                val toast = Toast.makeText(this, "Height or Weight cannot be 0", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                val height = height_string.toFloat()
                val weight = weight_string.toFloat()

                val bmi = weight / (height * height)
                lateinit var category: String

                when {
                    bmi < 18.5 -> category = "Underweight"
                    bmi in 18.5..24.9 -> category = "Normal Weight"
                    bmi in 25.0..29.9 -> category = "Overweight"
                }

                val text = "Your BMI is %.2f and you are %s".format(bmi, category)

                binding.tvResult.text = text
            }
        }
    }

    private fun reset() {
        binding.btnReset.setOnClickListener {
            binding.etHeight.text.clear()
            binding.etWeight.text.clear()
            binding.tvResult.text = ""
        }
    }
}