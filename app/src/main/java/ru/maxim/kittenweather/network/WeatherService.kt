package ru.maxim.kittenweather.network

import retrofit2.Response
import retrofit2.http.*
import ru.maxim.kittenweather.model.response.WeatherResponse

interface WeatherService {

    @GET("/data/2.5/onecall")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @QueryMap defaults: Map<String, String>
    ): Response<WeatherResponse>
}