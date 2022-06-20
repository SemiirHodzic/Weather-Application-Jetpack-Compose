package com.example.koinsample.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.koinsample.data.dto.city.CityCollection
import com.example.koinsample.ui.viewmodel.MainViewModel

@Composable
fun SearchCityScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val listOfCities by viewModel.listOfCities.observeAsState(null)

    ListOfCities(listOfCities)

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                SearchCityTextField(listOfCities, viewModel, navController)
            }
        })
}

@Composable
fun SearchCityTextField(
    listOfCities: CityCollection?,
    viewModel: MainViewModel,
    navController: NavController,
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Search city") },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                //navController.navigate(route = Screen.ShowForecast.navigateWithArguments(text))
                viewModel.getListOfCities(text)
                println("SearchCityScreen: ${listOfCities}")
            }
        ), singleLine = true
    )
}

@Composable
fun ListOfCities(
    listOfCities: CityCollection?,
) {
    LazyColumn() {
        listOfCities?.list?.forEach { dayForecast ->
            item {
                Text(text = dayForecast.name)
            }
        }
    }
}

@Preview(
    name = "Search Textfield",
    showBackground = true,
)
@Composable
fun PreviewScreen() {
    //SearchCityScreen(navController = rememberNavController())
}