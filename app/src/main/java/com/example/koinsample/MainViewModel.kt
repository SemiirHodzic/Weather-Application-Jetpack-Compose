package com.example.koinsample

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import retrofit2.HttpException
import java.lang.Error

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _city: MutableState<String> =
        mutableStateOf(value = "")

    val searchCity: State<String> = _city

    fun updateSearchCityState(city: String) {
        _city.value = city
    }

    suspend fun getCityData(): City {
        return try {
            repository.getCityData(_city.value)
        } catch (e: HttpException) {
            // 5xx - responses
            println("Error: ${e.response()}")
            City("", Main(0.0, 0))
        }

    }
}