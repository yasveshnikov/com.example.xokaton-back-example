package com.example.cats

import com.example.models.CatFactResponse
import retrofit2.Call
import retrofit2.http.GET

interface CatApi {

    @GET("/fact")
    fun getCatFact(): Call<CatFactResponse>
}