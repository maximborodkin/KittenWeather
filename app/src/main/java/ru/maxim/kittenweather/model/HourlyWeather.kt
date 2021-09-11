package ru.maxim.kittenweather.model

data class HourlyWeather (
    val dt: Long,
    val temp: Float,
    val pressure: Int,
    val clouds: Int,
    val weather: Weather
)

