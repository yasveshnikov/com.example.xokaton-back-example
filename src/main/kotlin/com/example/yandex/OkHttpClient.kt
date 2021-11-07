package com.example.yandex

import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.MINUTES
import java.util.concurrent.TimeUnit.SECONDS

object OkHttpClient {

    private const val baseUrl = "https://api.rasp.yandex.net/"

    private fun getDispatcher(): Dispatcher {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 30
        dispatcher.maxRequestsPerHost = 30
        return dispatcher
    }

    private fun getConnectionPool(): ConnectionPool = ConnectionPool(30, 10, MINUTES)

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, SECONDS)
            .readTimeout(30, SECONDS)
            .writeTimeout(30, SECONDS)
            .dispatcher(getDispatcher())
            .connectionPool(getConnectionPool())
            .retryOnConnectionFailure(true)
            .build()
    }

    fun getClient(): YandexApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(baseUrl)
            .build()
            .create(YandexApi::class.java)
    }
}