package com.example.classes.class_09.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.classes.class_09.model.Inventory


@Dao
interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save_inventory(inventory: Inventory)

    @Query("SELECT * FROM Inventory")
    suspend fun get_inventory_list(): MutableList <Inventory>

    @Delete
    suspend fun delete_inventory(inventory: Inventory)

    @Update
    suspend fun update_inventory(inventory: Inventory)
}