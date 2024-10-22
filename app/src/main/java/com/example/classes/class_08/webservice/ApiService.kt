package com.example.classes.class_08.webservice

import com.example.classes.class_08.model.ProductModelResponse
import com.example.classes.class_08.utils.Constants.END_POINT
import retrofit2.http.GET


interface ApiService {
    @GET(END_POINT)
    suspend fun get_products(): MutableList <ProductModelResponse>
}