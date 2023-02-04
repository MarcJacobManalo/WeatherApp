package com.example.weatherapp.weather.domain.model

import android.util.Log
import com.example.weatherapp.core.util.extension.convertUnixTimeToDayOfWeek
import com.example.weatherapp.core.util.extension.convertUnixTimeToLocalTime

enum class WeatherDescription {
    SUNNY, RAINY, UNKNOWN
}

data class Weather(
    val description: String?,
    val temperature: Double?,
    val sunrise: Long?,
    val sunset: Long?,
    val forecastDate: Long
) {

    fun getDescription(): WeatherDescription {
        return when (description) {
            null -> WeatherDescription.UNKNOWN
            "Rain" -> WeatherDescription.RAINY
            else -> WeatherDescription.SUNNY
        }
    }

    fun getSunriseDateTime(): String {
        return sunrise?.convertUnixTimeToLocalTime() ?: ""
    }

    fun getSunsetDateTime(): String {
        return sunset?.convertUnixTimeToLocalTime() ?: ""
    }

    fun getForecastDateTime(): String {
        return forecastDate.convertUnixTimeToLocalTime()
    }

    fun getDayOfWeekOfForecastDate(): String {
        return forecastDate.convertUnixTimeToDayOfWeek()
    }

}