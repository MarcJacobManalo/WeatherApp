package com.example.weatherapp.weather.data.source

import com.example.weatherapp.weather.data.model.WeatherDto
import com.example.weatherapp.weather.data.model.WeatherListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun fetchCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String = "metric",
    ): Response<WeatherDto>

    @GET("forecast")
    suspend fun fetchWeathers(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String = "metric",
    ): Response<WeatherListDto>
}