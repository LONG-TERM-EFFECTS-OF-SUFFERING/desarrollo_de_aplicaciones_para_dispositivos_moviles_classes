package com.example.classes.class_09.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.classes.R
import com.example.classes.databinding.Class08ItemDetailsBinding
import com.example.classes.class_09.model.Inventory
import com.example.classes.class_09.viewmodel.InventoryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ItemDetailsFragment : Fragment() {
    private lateinit var binding: Class08ItemDetailsBinding
    private val inventoryViewModel: InventoryViewModel by viewModels()
    private lateinit var receivedInventory: Inventory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Class08ItemDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inventory_data()
        controllers()
    }

    private fun controllers() {
        binding.btnDelete.setOnClickListener {
            delete_inventory()
        }

        binding.fbEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("inventory", receivedInventory)
            findNavController().navigate(R.id.action_itemDetailsFragment_to_itemEditFragment, bundle)
        }
    }

    private fun inventory_data() {
        val receivedBundle = arguments
        receivedInventory = receivedBundle?.getSerializable("inventory") as Inventory
        binding.tvItem.text = "${receivedInventory.name}"
        binding.tvPrice.text = "$ ${receivedInventory.price}"
        binding.tvQuantity.text = "${receivedInventory.quantity}"
        binding.tvTotalNumber.text = "$ ${
            inventoryViewModel.product_total(
                receivedInventory.price,
                receivedInventory.quantity
            )
        }"
    }

    private fun delete_inventory() {
        inventoryViewModel.delete_inventory(receivedInventory)
        inventoryViewModel.get_inventory_list()
        findNavController().popBackStack()
    }

}