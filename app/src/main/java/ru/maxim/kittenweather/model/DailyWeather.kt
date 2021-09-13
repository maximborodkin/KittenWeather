package ru.maxim.kittenweather.model

data class DailyWeather(
    val dt: Long,
    val temp: DailyTemp,
    val pressure: Int,
    val clouds: Int,
    val weather: ArrayList<Weather>
) {
}
    data class DailyTemp (
        val day: Double,
        val min: Double,
        val max: Double,
        val night: Double,
        val eve: Double,
        val morn: Double
    )
