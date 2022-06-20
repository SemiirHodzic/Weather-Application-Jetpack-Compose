package com.example.koinsample.ui.main

sealed class Screen(val route: String) {
    object ShowForecast : Screen("/show_forecast_screen")
    object SearchCity : Screen("/select_city_screen")

    fun navigateWithArguments(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
