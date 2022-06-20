package com.example.koinsample.repository

import com.example.koinsample.data.dto.city.CityCollection
import com.example.koinsample.data.dto.forecast.WeatherForecast
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherFor(city: String?): Response<WeatherForecast>
    suspend fun getCitiesFromQuery(city: String): Response<CityCollection>
}