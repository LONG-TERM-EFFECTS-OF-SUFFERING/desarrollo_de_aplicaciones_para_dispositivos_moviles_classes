package com.example.classes.class_09.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.classes.databinding.Class08AddItemBinding
import com.example.classes.class_09.model.Inventory
import com.example.classes.class_09.viewmodel.InventoryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddItemFragment : Fragment() {
    private lateinit var binding: Class08AddItemBinding
    private val inventory_view_model: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Class08AddItemBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controllers()
        product_list_observer()
    }

    private fun controllers() {
        valid_data()
        binding.btnSaveInventory.setOnClickListener {
            saveInvetory()
        }
    }

    private fun saveInvetory(){
        val name = binding.etName.text.toString()
        val price = binding.etPrice.text.toString().toInt()
        val quantity = binding.etQuantity.text.toString().toInt()
        val inventory = Inventory(name = name, price = price, quantity = quantity)

        inventory_view_model.save_inventory(inventory)
        Log.d("Inventory saved", inventory.toString())
        Toast.makeText(context,"Inventory saved", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()

    }

    private fun valid_data() {
        val edit_texts = listOf(binding.etName, binding.etPrice, binding.etQuantity)

        for (edit_text in edit_texts) {
            edit_text.addTextChangedListener {
                val are_all_fields_filled = edit_texts.all{
                    it.text.isNotEmpty()
                }
                binding.btnSaveInventory.isEnabled = are_all_fields_filled
            }
        }
    }

    private fun product_list_observer() {
        inventory_view_model.get_products()
        inventory_view_model.product_list.observe(viewLifecycleOwner){ list ->

            val product = list[2]

            Glide.with(binding.root.context).load(product.id).into(binding.ivApiImage)
            binding.tvProductTitle.text = product.title
        }
    }
}