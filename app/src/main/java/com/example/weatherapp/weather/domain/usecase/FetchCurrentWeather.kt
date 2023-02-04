package com.example.weatherapp.weather.domain.usecase

import com.example.weatherapp.weather.domain.model.Weather
import com.example.weatherapp.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class FetchCurrentWeather @Inject constructor(
    private val weatherRepository: WeatherRepository,
) {
    suspend fun execute(lat: Double, lon: Double): Flow<Weather?> {
        return flow {
            emit(
                weatherRepository.fetchCurrentWeather(
                    lat = lat,
                    lon = lon
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}