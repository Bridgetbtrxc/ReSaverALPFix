package com.elflin.movieapps.retrofit
import com.elflin.movieapps.data.EndPointAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: EndPointAPI by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8000/api")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EndPointAPI::class.java)
    }
}