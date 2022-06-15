package com.example.koinsample.data.network

import com.example.koinsample.data.dto.WeatherForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast/")
    suspend fun getCityData(
        @Query("q") q: String?,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Response<WeatherForecast>
}