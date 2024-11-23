package com.example.classes.class_09.repository


import com.example.classes.class_09.data.InventoryDao
import com.example.classes.class_09.model.Inventory
import com.example.classes.class_09.model.ProductModelResponse
import com.example.classes.class_09.webservice.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class InventoryRepository @Inject constructor(
    private val inventoryDao: InventoryDao,
    private val api_service: ApiService
) {



    suspend fun save_inventory(inventory:Inventory){
        withContext(Dispatchers.IO){
            inventoryDao.save_inventory(inventory)
        }
    }

    suspend fun get_inventory_list(): MutableList <Inventory> {
        return withContext(Dispatchers.IO){
            inventoryDao.get_inventory_list()
        }
    }

    suspend fun delete_inventory(inventory: Inventory) {
        withContext(Dispatchers.IO){
            inventoryDao.delete_inventory(inventory)
        }
    }

    suspend fun update_inventory(inventory: Inventory) {
        withContext(Dispatchers.IO){
            inventoryDao.update_inventory(inventory)
        }
    }

    suspend fun get_products(): MutableList<ProductModelResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api_service.get_products()
                response
            } catch (e: Exception) {
                e.printStackTrace()
                mutableListOf()
            }
        }
    }
}