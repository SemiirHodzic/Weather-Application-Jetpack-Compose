package com.example.koinsample


import com.example.koinsample.data.network.WeatherApi
import com.example.koinsample.data.repository.WeatherRepository
import com.example.koinsample.data.repository.WeatherRepositoryImpl
import com.example.koinsample.ui.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }

    single(named("IODispatcher")) {
        Dispatchers.IO
    }

    viewModel{
        MainViewModel(get(), get(named("IODispatcher")))
    }
}