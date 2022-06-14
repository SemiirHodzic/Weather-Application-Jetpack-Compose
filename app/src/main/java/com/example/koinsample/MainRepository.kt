package com.example.koinsample

interface MainRepository {
    suspend fun getCityData(city: String): City
}