package com.example.classes.class_06

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.classes.R
import com.example.classes.databinding.Class06OutputFragmentBinding


class OutputFragment : Fragment() {
    private lateinit var binding: Class06OutputFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Class06OutputFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigate_to_input_fragment()
        capture_and_process_data()
    }

    private fun navigate_to_input_fragment() {
        binding.btnCalculateAnotherGrade.setOnClickListener {
            findNavController().navigate(R.id.action_outputFragment_to_inputFragment)
        }
    }

    private fun capture_and_process_data() {
        val text_view = binding.tvResult
        val student = arguments?.getSerializable("student") as Class06Student
        val name = student.name
        val grade_1 = student.grade_1
        val grade_2 = student.grade_2
        val average = (grade_1 + grade_2) / 2

        val text = "Name: %s\nGrade 1: %.2f\nGrade 2: %.2f\nResult: %.2f".format(name, grade_1, grade_2, average)

        text_view.text = text
    }
}
