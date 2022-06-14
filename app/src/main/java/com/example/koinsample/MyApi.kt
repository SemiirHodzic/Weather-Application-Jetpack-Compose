package com.example.koinsample

import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    // Ktor get client
    @GET("weather/")
    suspend fun getCityData(
        @Query("q") q: String,
        @Query("appid") appId: String
    ): City
}