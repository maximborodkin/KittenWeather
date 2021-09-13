package ru.maxim.kittenweather.model.response

import ru.maxim.kittenweather.model.DailyWeather
import ru.maxim.kittenweather.model.WeatherUnit

data class WeatherResponse(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val current: WeatherUnit,
    val hourly: ArrayList<WeatherUnit>,
    val daily: ArrayList<DailyWeather>
)