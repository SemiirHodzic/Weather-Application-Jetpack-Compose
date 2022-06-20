package com.example.koinsample.di


import com.example.koinsample.data.network.CityApi
import com.example.koinsample.data.network.WeatherApi
import com.example.koinsample.repository.WeatherRepository
import com.example.koinsample.repository.WeatherRepositoryImpl
import com.example.koinsample.ui.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = module {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    single {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(WeatherApi::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(CityApi::class.java)
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(get(), get())
    }

    single(named("IODispatcher")) {
        Dispatchers.IO
    }

    viewModel {
        MainViewModel(get(), get(named("IODispatcher")))
    }
}