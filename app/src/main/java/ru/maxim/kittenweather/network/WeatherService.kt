package ru.maxim.kittenweather.network

import retrofit2.Call
import ru.maxim.kittenweather.model.response.WeatherResponse

interface WeatherService {
    fun getWeather(): Call<WeatherResponse>
}