package com.example.koinsample.ui.main

sealed class Screen(val route: String) {
    object ShowForecast : Screen("/show_forecast_screen")
    object SelectCity : Screen("/select_city_screen")
}
