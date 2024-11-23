package com.example.classes.class_09.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.classes.class_09.model.Inventory
import com.example.classes.class_09.utils.Constants.NAME_BD


@Database(entities = [Inventory::class], version = 1,  exportSchema = false)
abstract class InventoryDB : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao
}