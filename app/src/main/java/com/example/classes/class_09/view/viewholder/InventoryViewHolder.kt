package com.example.classes.class_09.view.viewholder

import android.os.Bundle
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.classes.R
import com.example.classes.databinding.Class08ItemBinding
import com.example.classes.class_09.model.Inventory


class InventoryViewHolder(binding: Class08ItemBinding, navController: NavController) :
    RecyclerView.ViewHolder(binding.root) {
    val bindingItem = binding
    val navController = navController


    fun setItemInventory(inventory: Inventory) {
        bindingItem.tvName.text = inventory.name
        bindingItem.tvPrice.text = "$${inventory.price}"
        bindingItem.tvQuantity.text = "${inventory.quantity}"

        bindingItem.cvInventory.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("inventory", inventory)
            navController.navigate(R.id.action_homeInventoryFragment_to_itemDetailsFragment, bundle)
        }

    }
}