package com.example.koinsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import com.example.koinsample.ui.theme.KoinSampleTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = getViewModel<MainViewModel>()

                    viewModel.viewModelScope.launch {
                        val weather = viewModel.getCityData()

                        val temp = weather.main.temp - 273.15
                        val rounded = round(temp).toInt()

                        println("VM : ${rounded}")

                    }
                }
            }
        }
    }
}

