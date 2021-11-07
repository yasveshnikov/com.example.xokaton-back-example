package com.example.yandex

import com.example.models.NearStationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YandexApi {
    @GET("/nearest_stations")
    fun getNearStations(
        @Query("apikey") authKey: String,
        @Query("lat") lat: Float,
        @Query("lng") lng: Float,
        @Query("distance") distance: Int
    ): Call<NearStationResponse>
}