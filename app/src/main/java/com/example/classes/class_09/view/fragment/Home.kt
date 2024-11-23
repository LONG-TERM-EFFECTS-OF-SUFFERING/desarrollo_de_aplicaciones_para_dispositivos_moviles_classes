package com.example.classes.class_09.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.classes.R
import com.example.classes.databinding.Class08HomeBinding
import com.example.classes.class_09.view.adapter.InventoryAdapter
import com.example.classes.class_09.viewmodel.InventoryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: Class08HomeBinding
    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Class08HomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controllers()
        inventory_list_observer()
        progress_observer()
    }

    private fun controllers() {
        binding.fbAddItem.setOnClickListener {
            findNavController().navigate(R.id.action_homeInventoryFragment_to_addItemFragment)
        }
    }

    private fun inventory_list_observer() {
        inventoryViewModel.get_inventory_list()
        inventoryViewModel.inventory_list.observe(viewLifecycleOwner){ listInventory ->
            val recycler = binding.recyclerview
            val layoutManager =LinearLayoutManager(context)
            recycler.layoutManager = layoutManager
            val adapter = InventoryAdapter(listInventory, findNavController())
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
    
    private fun progress_observer() {
        inventoryViewModel.progress_state.observe(viewLifecycleOwner){ status ->
            binding.progress.isVisible = status
        }
    }
}
