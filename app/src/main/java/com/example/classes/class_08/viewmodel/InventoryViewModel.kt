package com.example.classes.class_08.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.classes.class_08.model.Inventory
import com.example.classes.class_08.model.ProductModelResponse
import com.example.classes.class_08.repository.InventoryRepository
import kotlinx.coroutines.launch


class InventoryViewModel(application: Application) : AndroidViewModel(application) {
    val context = getApplication <Application>()
    private val inventory_repository = InventoryRepository(context)

    private val _inventory_list = MutableLiveData <MutableList <Inventory>>()
    val inventory_list: LiveData <MutableList<Inventory>> get() = _inventory_list

    private val _progress_state = MutableLiveData(false)
    val progress_state: LiveData <Boolean> = _progress_state

    private val _product_list = MutableLiveData <MutableList <ProductModelResponse>>()
    val product_list: LiveData <MutableList <ProductModelResponse>> = _product_list

    fun save_inventory(inventory: Inventory) {
        viewModelScope.launch {

            _progress_state.value = true
            try {
                inventory_repository.save_inventory(inventory)
                _progress_state.value = false
            } catch (e: Exception) {
                _progress_state.value = false
            }
        }
    }

    fun get_inventory_list() {
        viewModelScope.launch {
            _progress_state.value = true
            try {
                _inventory_list.value = inventory_repository.get_inventory_list()
                _progress_state.value = false
            } catch (e: Exception) {
                _progress_state.value = false
            }

        }
    }

    fun delete_inventory(inventory: Inventory) {
        viewModelScope.launch {
            _progress_state.value = true
            try {
                inventory_repository.delete_inventory(inventory)
                _progress_state.value = false
            } catch (e: Exception) {
                _progress_state.value = false
            }

        }
    }

    fun update_inventory(inventory: Inventory) {
        viewModelScope.launch {
            _progress_state.value = true
            try {
                inventory_repository.update_inventory(inventory)
                _progress_state.value = false
            } catch (e: Exception) {
                _progress_state.value = false
            }
        }
    }

    fun get_products() {
        viewModelScope.launch {
            _progress_state.value = true
            try {
                _product_list.value = inventory_repository.get_products()
                _progress_state.value = false

            } catch (e: Exception) {
                _progress_state.value = false
            }
        }
    }

    fun product_total(price: Int, quantity: Int): Double {
        val total = price * quantity
        return total.toDouble()
    }
}

