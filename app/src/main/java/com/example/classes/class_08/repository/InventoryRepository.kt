package com.example.classes.class_08.repository

import android.content.Context
import com.example.classes.class_08.data.InventoryDB
import com.example.classes.class_08.data.InventoryDao
import com.example.classes.class_08.model.Inventory
import com.example.classes.class_08.model.ProductModelResponse
import com.example.classes.class_08.webservice.ApiService
import com.example.classes.class_08.webservice.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InventoryRepository(val context: Context){
    private var inventoryDao:InventoryDao = InventoryDB.get_database(context).inventoryDao()
    private var api_service: ApiService = ApiUtils.getApiService()


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