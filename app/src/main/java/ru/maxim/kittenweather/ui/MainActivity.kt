package ru.maxim.kittenweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.maxim.kittenweather.R
import ru.maxim.kittenweather.databinding.ActivityMainBinding
import ru.maxim.kittenweather.databinding.ItemDailyWeatherBinding
import ru.maxim.kittenweather.databinding.ItemHourlyWeatherBinding
import ru.maxim.kittenweather.model.DailyWeather
import ru.maxim.kittenweather.model.WeatherUnit
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val model: WeatherViewModel by viewModels()
        model.getWeather().observe(this, { response ->
            binding?.temp?.text = getString(R.string.temp_placeholder, response.current.temp.toInt())
            binding?.description?.text = response.current.weather.first().description
            fillHourlyTemp(response.hourly)
            fillDailyTemp(response.daily)
        })
    }

    private fun fillDailyTemp(daily: ArrayList<DailyWeather>) {
        val dayOfWeekFormat = SimpleDateFormat("EE", Locale.getDefault())
        daily.forEach {
            val dailyViewBinding = ItemDailyWeatherBinding.inflate(
                layoutInflater,
                binding?.dailyWeatherHolder,
                true
            )
            dailyViewBinding.dailyDay.text = dayOfWeekFormat.format(Date(it.dt * 1000))
            dailyViewBinding.dailyDayTemp.text = getString(R.string.temp_placeholder, it.temp.day.toInt())
            dailyViewBinding.dailyNightTemp.text = getString(R.string.temp_placeholder,it.temp.night.toInt())
            dailyViewBinding.dailyImage.setImageResource(getIconByWeather(it.weather.first().id))
        }
    }

    private fun fillHourlyTemp(hourly: ArrayList<WeatherUnit>) {
        val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val today = dateFormat.format(Date())
        hourly.forEach {
            if (today == dateFormat.format(it.dt * 1000)) { // if today
                val hourlyViewBinding = ItemHourlyWeatherBinding.inflate(
                    layoutInflater,
                    binding?.hourlyWeatherHolder,
                    true
                )
                hourlyViewBinding.hourlyTime.text = timeFormat.format(Date(it.dt * 1000))
                hourlyViewBinding.hourlyTemp.text = getString(R.string.temp_placeholder, it.temp.toInt())
                hourlyViewBinding.hourlyImage.setImageResource(getIconByWeather(it.weather.first().id))
            }
        }
    }

    private fun getIconByWeather(id: Int): Int {
        return when (id) {
            in 200..202 -> R.drawable.ic_weather_lightning_rainy
            in 203..299 -> R.drawable.ic_weather_lightning
            in 300..520 -> R.drawable.ic_weather_rainy
            in 521..599 -> R.drawable.ic_weather_pouring
            in 600..699 -> R.drawable.ic_weater_snowy
            in 700..799 -> R.drawable.ic_weather_mist
            800 -> R.drawable.ic_weather_sunny
            in 801..802 -> R.drawable.ic_weather_partly_cloudy
            in 803..804 -> R.drawable.ic_weather_cloudy
            else -> 0
        }
    }
}