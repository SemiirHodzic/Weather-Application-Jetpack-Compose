package com.example.koinsample.repository

import com.example.koinsample.data.dto.city.CityCollection
import com.example.koinsample.data.dto.forecast.WeatherForecast
import com.example.koinsample.data.network.CityApi
import com.example.koinsample.data.network.WeatherApi
import retrofit2.Response
import com.example.koinsample.utils.metricUnits

class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val api2: CityApi
) : WeatherRepository {
    override suspend fun getWeatherFor(city: String?): Response<WeatherForecast> {
        return api.getCityWeather(city, metricUnits, "1487ee4774bdde15c075dab2c6f5ef13")
    }

    override suspend fun getCitiesFromQuery(city: String): Response<CityCollection> {
        return api2.getCityFromQuery(city, "1487ee4774bdde15c075dab2c6f5ef13")
    }
}