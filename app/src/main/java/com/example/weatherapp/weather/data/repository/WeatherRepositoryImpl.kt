package com.example.weatherapp.weather.data.repository

import com.example.weatherapp.weather.data.source.WeatherRemoteSource
import com.example.weatherapp.weather.domain.model.Weather
import com.example.weatherapp.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val source: WeatherRemoteSource) :
    WeatherRepository {

    override suspend fun fetchCurrentWeather(lat: Double, lon: Double): Weather? {
        return source.fetchCurrentWeather(lat = lat, lon = lon).body()?.toWeather()
    }

    override suspend fun fetchWeathers(lat: Double, lon: Double): List<Weather>? {
        return source.fetchWeathers(lat = lat, lon = lon).body()?.list?.map { it.toWeather() }
    }
}