package com.example.classes.class_09.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.classes.databinding.Class08ItemBinding
import com.example.classes.class_09.model.Inventory
import com.example.classes.class_09.view.viewholder.InventoryViewHolder


class InventoryAdapter(private val listInventory:MutableList<Inventory>, private val navController: NavController):RecyclerView.Adapter<InventoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val binding = Class08ItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return InventoryViewHolder(binding, navController)
    }

    override fun getItemCount(): Int {
        return listInventory.size
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val inventory = listInventory[position]
        holder.setItemInventory(inventory)
    }
}