package com.example.weatherapp.weather.domain.usecase

import com.example.weatherapp.weather.domain.model.Weather
import com.example.weatherapp.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchWeathers @Inject constructor (private val repository: WeatherRepository) {
    suspend fun execute(lat: Double, lon: Double): Flow<List<Weather>?> {
        return flow {
            emit(repository.fetchWeathers(lat = lat, lon = lon))
        }.flowOn(Dispatchers.IO)
    }
}