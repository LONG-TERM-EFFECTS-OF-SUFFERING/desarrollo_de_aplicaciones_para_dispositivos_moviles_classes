package com.example.classes.class_06

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.classes.R
import com.example.classes.databinding.Class06InputFragmentBinding


class InputFragment : Fragment() {
    private lateinit var binding: Class06InputFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Class06InputFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        println("The fragment is created")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigate_and_pass_data_to_output_fragment()
        binding.btnSendData.isEnabled = false
        enable_or_disable_button()

    }

    private fun is_any_field_empty() : Boolean {
        val name = binding.etName.text.toString()
        val grade_1 = binding.etGrade1.text.toString()
        val grade_2 = binding.etGrade2.text.toString()

        return name.isEmpty() || grade_1.isEmpty() || grade_2.isEmpty()
    }

    private fun enable_or_disable_button() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnSendData.isEnabled = !is_any_field_empty()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.etName.addTextChangedListener(textWatcher)
        binding.etGrade1.addTextChangedListener(textWatcher)
        binding.etGrade2.addTextChangedListener(textWatcher)
    }

    private fun navigate_and_pass_data_to_output_fragment() {
        binding.btnSendData.setOnClickListener {

            val name = binding.etName.text.toString()
            val grade_1 = binding.etGrade1.text.toString().toFloat()
            val grade_2 = binding.etGrade2.text.toString().toFloat()
            val student = Class06Student(name, grade_1, grade_2)

            val bundle = Bundle()
            bundle.putSerializable("student", student)

            findNavController().navigate(R.id.action_inputFragment_to_outputFragment, bundle)
        }
    }
}
