package com.example.cats

class CatClient {
    private val client = OkHttpClient.getClient()

    fun getFact() = client.getCatFact().execute().body()?.fact
}