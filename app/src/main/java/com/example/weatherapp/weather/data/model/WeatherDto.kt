package com.example.weatherapp.weather.data.model

import com.example.weatherapp.weather.domain.model.Weather

data class WeatherDto(
    val dt: Long,
    val weather: List<WeatherData>?,
    val main: MainData?,
    val sys: SysData?
) {
    fun toWeather() = Weather(
        description = weather?.firstOrNull()?.main,
        temperature = main?.temp,
        sunrise = sys?.sunrise,
        sunset = sys?.sunset,
        forecastDate = dt
    )
}

data class WeatherData(
    val main: String?,
)

data class MainData(
    val temp: Double?,
)

data class SysData(
    val sunrise: Long?,
    val sunset: Long?
)