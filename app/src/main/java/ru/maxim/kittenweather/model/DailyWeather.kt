package ru.maxim.kittenweather.model

data class DailyWeather(
    val dt: Long,
    val temp: DailyTemp,
    val pressure: Int,
    val clouds: Int,
    val weather: Weather
) {
    data class DailyTemp (
        val day: Float,
        val min: Float,
        val max: Float,
        val night: Float,
        val eve: Float,
        val morn: Float
    )
}