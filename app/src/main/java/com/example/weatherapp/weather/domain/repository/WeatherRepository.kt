package com.example.weatherapp.weather.domain.repository

import com.example.weatherapp.weather.domain.model.Weather

interface WeatherRepository {
    suspend fun fetchCurrentWeather(lat: Double, lon: Double) : Weather?
    suspend fun fetchWeathers(lat: Double, lon: Double) : List<Weather>?
}