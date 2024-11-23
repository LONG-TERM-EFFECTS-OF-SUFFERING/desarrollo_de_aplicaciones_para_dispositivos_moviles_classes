package com.example.classes.class_08.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.classes.class_08.model.Inventory
import com.example.classes.class_08.utils.Constants.NAME_BD


@Database(entities = [Inventory::class], version = 1,  exportSchema = false)
abstract class InventoryDB : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao

    companion object{
        fun get_database(context: Context): InventoryDB {
            return Room.databaseBuilder(
                context.applicationContext,
                InventoryDB::class.java,
                NAME_BD
            ).build()
        }
    }
}
