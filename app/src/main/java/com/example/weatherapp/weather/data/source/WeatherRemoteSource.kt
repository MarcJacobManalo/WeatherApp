package com.example.weatherapp.weather.data.source

import android.app.ActivityManager
import android.content.Context
import com.example.weatherapp.R
import com.example.weatherapp.weather.data.model.WeatherDto
import com.example.weatherapp.weather.data.model.WeatherListDto
import retrofit2.Response
import javax.inject.Inject

class WeatherRemoteSource @Inject constructor(
   private val api: WeatherApi,
   private val context: Context
) {

     suspend fun fetchCurrentWeather(lat: Double, lon: Double): Response<WeatherDto> {
        return api.fetchCurrentWeather(lat = lat, lon = lon, appid = context.getString(R.string.weather_api_key))
    }

    suspend fun fetchWeathers(lat: Double, lon: Double): Response<WeatherListDto> {
        return api.fetchWeathers(lat = lat, lon = lon, appid = context.getString(R.string.weather_api_key))
    }
}