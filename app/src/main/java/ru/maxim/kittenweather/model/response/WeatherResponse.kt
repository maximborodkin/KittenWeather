package ru.maxim.kittenweather.model.response

import ru.maxim.kittenweather.model.DailyWeather
import ru.maxim.kittenweather.model.HourlyWeather
import ru.maxim.kittenweather.model.Weather

data class WeatherResponse(
    val lat: Float,
    val lon: Float,
    val timezone: String,
    val current: Weather,
    val hourly: ArrayList<HourlyWeather>,
    val daily: ArrayList<DailyWeather>
)