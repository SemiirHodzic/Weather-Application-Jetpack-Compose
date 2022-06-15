package com.example.koinsample.data.repository

import com.example.koinsample.data.dto.WeatherForecast
import com.example.koinsample.data.network.WeatherApi
import retrofit2.Response

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherFor(city: String): Response<WeatherForecast> {
        return api.getCityData(city, "metric", "1487ee4774bdde15c075dab2c6f5ef13")
    }
}