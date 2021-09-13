package ru.maxim.kittenweather.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val baseUrl = "https://api.openweathermap.org"
    private val loggingInterceptor = HttpLoggingInterceptor().also { it.level = BODY }
    private val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getWeatherService(): WeatherService = retrofit.create(WeatherService::class.java)
}
