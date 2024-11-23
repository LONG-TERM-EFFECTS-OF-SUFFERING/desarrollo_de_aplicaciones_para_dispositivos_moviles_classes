package com.example.classes.class_09.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.classes.R
import com.example.classes.databinding.Class08EditItemBinding
import com.example.classes.class_09.model.Inventory
import com.example.classes.class_09.viewmodel.InventoryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ItemEditFragment : Fragment() {
    private lateinit var binding: Class08EditItemBinding
    private val inventoryViewModel: InventoryViewModel by viewModels()
    private lateinit var receivedInventory: Inventory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Class08EditItemBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inventory_data()
        controllers()
    }

    private fun controllers() {
        binding.btnEdit.setOnClickListener {
            update_inventory()
        }
    }

    private fun inventory_data() {
        val receivedBundle = arguments
        receivedInventory = receivedBundle?.getSerializable("inventory") as Inventory
        binding.etName.setText(receivedInventory.name)
        binding.etPrice.setText(receivedInventory.price.toString())
        binding.etQuantity.setText(receivedInventory.quantity.toString())
    }

    private fun update_inventory(){
        val name = binding.etName.text.toString()
        val price = binding.etPrice.text.toString().toInt()
        val quantity = binding.etQuantity.text.toString().toInt()
        val inventory = Inventory(receivedInventory.id, name,price,quantity)

        inventoryViewModel.update_inventory(inventory)
        findNavController().navigate(R.id.action_itemEditFragment_to_homeInventoryFragment)
    }
}