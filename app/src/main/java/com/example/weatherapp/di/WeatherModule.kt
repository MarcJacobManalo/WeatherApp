package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.core.domain.repository.LocationRepository
import com.example.weatherapp.weather.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.weather.data.source.WeatherApi
import com.example.weatherapp.weather.data.source.WeatherRemoteSource
import com.example.weatherapp.weather.domain.repository.WeatherRepository
import com.example.weatherapp.weather.domain.usecase.FetchCurrentWeather
import com.example.weatherapp.weather.domain.usecase.FetchWeathers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherRemoteSource(api: WeatherApi, app: Application): WeatherRemoteSource {
        return WeatherRemoteSource(api = api, context = app)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(source: WeatherRemoteSource): WeatherRepository {
        return WeatherRepositoryImpl(source = source)
    }

    @Singleton
    @Provides
    fun provideFetchCurrentWeather(weatherRepository: WeatherRepository): FetchCurrentWeather {
        return FetchCurrentWeather(weatherRepository = weatherRepository)
    }

    @Singleton
    @Provides
    fun provideFetchWeathers(repository: WeatherRepository): FetchWeathers {
        return FetchWeathers(repository = repository)
    }
}