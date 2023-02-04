package com.example.weatherapp.weather.data.model

import com.example.weatherapp.weather.domain.model.Weather

data class WeatherListDto(val list: List<WeatherDataList>?)

data class WeatherDataList(
    val dt: Long,
    val main: MainData?,
    val weather: List<WeatherData>?,
) {
    fun toWeather() = Weather(
        description = weather?.firstOrNull()?.main,
        temperature = main?.temp,
        sunrise = null,
        sunset = null,
        forecastDate = dt
    )
}


