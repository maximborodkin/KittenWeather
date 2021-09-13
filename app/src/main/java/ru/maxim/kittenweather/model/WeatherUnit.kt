package ru.maxim.kittenweather.model

data class WeatherUnit (
    val dt: Long,
    val temp: Double,
    val pressure: Int,
    val clouds: Int,
    val weather: ArrayList<Weather>
)