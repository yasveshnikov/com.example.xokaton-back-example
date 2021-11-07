package com.example.cats

import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object OkHttpClient {

    private const val baseUrl = "https://catfact.ninja"

    private fun getDispatcher(): Dispatcher {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 30
        dispatcher.maxRequestsPerHost = 30
        return dispatcher
    }

    private fun getConnectionPool(): ConnectionPool = ConnectionPool(30, 10, TimeUnit.MINUTES)

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .dispatcher(getDispatcher())
            .connectionPool(getConnectionPool())
            .retryOnConnectionFailure(true)
            .build()
    }

    fun getClient(): CatApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(baseUrl)
            .build()
            .create(CatApi::class.java)
    }
}