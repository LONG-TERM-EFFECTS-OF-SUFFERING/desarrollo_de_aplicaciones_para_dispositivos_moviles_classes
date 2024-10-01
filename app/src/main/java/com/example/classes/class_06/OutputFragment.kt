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
    ): View? {
        binding = Class06OutputFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //navigate_to_input_fragment()
    }

//    private fun navigate_to_input_fragment(){
//        binding.btn.setOnClickListener {
//            findNavController().navigate(R.id.action_fragmentA_to_fragmentB)
//        }
//    }

}
