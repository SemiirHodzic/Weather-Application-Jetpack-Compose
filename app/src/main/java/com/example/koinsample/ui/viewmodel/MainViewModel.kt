package com.example.koinsample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koinsample.data.dto.WeatherForecast
import com.example.koinsample.data.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: WeatherRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _forecast = MutableLiveData<WeatherForecast?>()
    val forecast: LiveData<WeatherForecast?> = _forecast

    private val _error = MutableLiveData<String>()


    fun getWeatherFor(city: String?) {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = repository.getWeatherFor(city = city)
                val weatherForecast = response.body()

                if (response.isSuccessful && weatherForecast != null) {
                    _forecast.postValue(weatherForecast)
                } else {
                    _error.postValue("Error: HttpCode(${response.code()})")
                }
            } catch (t: Throwable) {
                _error.postValue(t.message)
            }
        }
    }
}