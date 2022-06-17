package com.example.koinsample.repository

import com.example.koinsample.data.dto.WeatherForecast
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherFor(city: String?): Response<WeatherForecast>
}