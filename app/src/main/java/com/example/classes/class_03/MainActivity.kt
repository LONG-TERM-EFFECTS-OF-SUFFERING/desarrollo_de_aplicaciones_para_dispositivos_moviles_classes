package com.example.classes.class_03

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.classes.R
import com.example.classes.databinding.Class03Binding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: Class03Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_03)
        binding = DataBindingUtil.setContentView(this, R.layout.class_03)

        binding.btnCalculate.isEnabled = false
        calculate()
        enable_or_disable_button()
    }

    private fun is_any_field_empty() : Boolean {
        val name = binding.etName.text.toString()
        val course = binding.etCourse.text.toString()
        val grade_1 = binding.etGrade1.text.toString()
        val grade_2 = binding.etGrade2.text.toString()

        return name.isEmpty() || course.isEmpty() || grade_1.isEmpty() || grade_2.isEmpty()
    }

    private fun enable_or_disable_button() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnCalculate.isEnabled = !is_any_field_empty()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.etName.addTextChangedListener(textWatcher)
        binding.etCourse.addTextChangedListener(textWatcher)
        binding.etGrade1.addTextChangedListener(textWatcher)
        binding.etGrade2.addTextChangedListener(textWatcher)
    }

    private fun calculate() {
        binding.btnCalculate.setOnClickListener {
            val name = binding.etName.text.toString()
            val course = binding.etCourse.text.toString()
            val is_checked = binding.cbRepeatedCourse.isChecked
            val grade_1 = binding.etGrade1.text.toString().toFloat()
            val grade_2 = binding.etGrade2.text.toString().toFloat()

            if (grade_1 < 0 || grade_1 > 5 || grade_2 < 0 || grade_2 > 5)
                Toast.makeText(this, "Grades must be between 0 and 5", Toast.LENGTH_SHORT).show()
            else {
                val student = Class03Student(name, course, grade_1, grade_2, is_checked)
                val result = student.calculate_result()

                val text = "Name: %s\nCourse: %s\nGrade 1: %.2f\nGrade 2: %.2f\nResult: %.2f"
                            .format(student.name, student.course, student.grade_1, student.grade_2, result)

                binding.tvResult.text = text

                clear_all_fields()
            }
        }
    }

    private fun clear_all_fields() {
        binding.etName.text.clear()
        binding.etCourse.text.clear()
        binding.cbRepeatedCourse.isChecked = false
        binding.etGrade1.text.clear()
        binding.etGrade2.text.clear()
    }
}