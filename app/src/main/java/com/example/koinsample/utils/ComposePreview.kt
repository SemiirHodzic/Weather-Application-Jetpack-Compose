package com.example.koinsample.utils

import androidx.compose.runtime.Composable
import com.example.koinsample.data.dto.WeatherForecast
import com.google.gson.Gson

@Composable
fun loadPreviewResponse(): WeatherForecast {
    return Gson().fromJson(previewResponse, WeatherForecast::class.java)
}