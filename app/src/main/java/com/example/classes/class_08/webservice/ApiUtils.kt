package com.example.classes.class_08.webservice

class ApiUtils {
    companion object{
        fun getApiService():ApiService{
            return RetrofitClient.get_retrofit().create(ApiService::class.java)
        }
    }
}