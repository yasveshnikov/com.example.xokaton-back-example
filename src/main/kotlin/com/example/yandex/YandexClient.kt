package com.example.yandex

import com.example.models.NearStationResponse
import retrofit2.Response

class YandexClient {
    private val rStation = 50
    private val authKey = "3dbec502-678f-442d-9fc5-e2b82b3e6eb7"

    private val client = OkHttpClient.getClient()

    fun getNearStation(lat: Float, lng: Float): Response<NearStationResponse> =
        client.getNearStations(authKey, lat, lng, rStation).execute()
}