package com.example.koinsample

class MainRepositoryImpl(
    private val api: MyApi
): MainRepository {
    override suspend fun getCityData(city: String): City {
       return api.getCityData(city, "1487ee4774bdde15c075dab2c6f5ef13")
    }
}