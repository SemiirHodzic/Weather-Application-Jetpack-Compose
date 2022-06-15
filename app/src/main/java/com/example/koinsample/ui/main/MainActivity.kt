package com.example.koinsample.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.koinsample.ui.screens.MainScreen
import com.example.koinsample.ui.theme.KoinSampleTheme
import com.example.koinsample.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinSampleTheme {
                val viewModel = getViewModel<MainViewModel>()
                MainScreen(viewModel)
            }
        }
    }
}

