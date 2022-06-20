package com.example.koinsample.data.network

import com.example.koinsample.data.dto.city.CityCollection
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {
    @GET("geo/1.0/direct")
    suspend fun getCityFromQuery(
        @Query("q") q: String?,
        @Query("appid") appId: String
    ): Response<CityCollection>
}