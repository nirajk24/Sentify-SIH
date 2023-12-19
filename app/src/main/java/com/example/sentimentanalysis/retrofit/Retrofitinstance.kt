package com.example.sentimentanalysis.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: APIService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.30.41.206:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}