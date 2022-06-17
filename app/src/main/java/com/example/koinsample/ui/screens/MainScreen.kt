package com.example.koinsample.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.koinsample.ui.main.Screen
import com.example.koinsample.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SelectCity.route) {

        composable(
            route = Screen.SelectCity.route,
        ) {
            SelectCityScreen(navController)
        }

        composable(
            route = Screen.ShowForecast.route + "/{city}",
            arguments = listOf(
                navArgument("city") {
                    type = NavType.StringType
                    defaultValue = "Stockholm"
                    nullable = true
                }
            )
        ) {
            ShowForecastScreen(
                city = it.arguments?.getString("city"),
                viewModel = viewModel,
            )
        }
    }
}