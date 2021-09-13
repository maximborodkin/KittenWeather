package ru.maxim.kittenweather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.maxim.kittenweather.model.response.WeatherResponse
import ru.maxim.kittenweather.network.RetrofitClient

class WeatherViewModel : ViewModel() {
    private val weatherService = RetrofitClient.getWeatherService()
    private val weatherInfo: MutableLiveData<WeatherResponse> = MutableLiveData()

    init {
        loadWeather()
    }

    private fun loadWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = weatherService.getWeather(55.7522, 37.6156, getDefaultFields())
            if (response.isSuccessful && response.body() != null) {
                GlobalScope.launch(Dispatchers.Main){ weatherInfo.value = response.body() }
            }
        }
    }

    fun getWeather() = weatherInfo

    private fun getDefaultFields(): Map<String, String> {
        val defaults = HashMap<String, String>()
        defaults["units"] = "metric"
        defaults["appid"] = "ac185dffa9451956a19d4794d4e9ee9c"
        defaults["exclude"] = "minutely"
        return defaults
    }
}