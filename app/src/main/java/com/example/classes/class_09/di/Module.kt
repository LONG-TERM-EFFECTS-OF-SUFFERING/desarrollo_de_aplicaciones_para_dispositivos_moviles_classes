package com.example.classes.class_09.di

import android.content.Context
import androidx.room.Room
import com.example.classes.class_09.data.InventoryDB
import com.example.classes.class_09.data.InventoryDao
import com.example.classes.class_09.utils.Constants
import com.example.classes.class_09.utils.Constants.BASE_URL
import com.example.classes.class_09.webservice.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provide_inventory_DB(@ApplicationContext context: Context): InventoryDB {
        return Room.databaseBuilder(
            context,
            InventoryDB::class.java,
            Constants.NAME_BD
        ).build()
    }

    @Singleton
    @Provides
    fun provide_retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provide_api_service(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provide_inventory_dao(inventoryDB:InventoryDB): InventoryDao {
        return inventoryDB.inventoryDao()
    }

}