package com.example.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.core.data.repository.LocationRepositoryImpl
import com.example.weatherapp.core.data.repository.TimeRepositoryImpl
import com.example.weatherapp.core.data.repository.UserRepositoryImpl
import com.example.weatherapp.core.data.source.LocationSource
import com.example.weatherapp.core.data.source.SharedPref
import com.example.weatherapp.core.data.source.TimeSource
import com.example.weatherapp.core.data.source.UserDatabase
import com.example.weatherapp.core.domain.repository.LocationRepository
import com.example.weatherapp.core.domain.repository.TimeRepository
import com.example.weatherapp.core.domain.repository.UserRepository
import com.example.weatherapp.core.domain.usecase.GetLastKnownLocation
import com.example.weatherapp.core.domain.usecase.IsCurrentTimePassed6pm
import com.example.weatherapp.login.domain.usecase.FetchCurrentUser
import com.example.weatherapp.login.domain.usecase.Login
import com.example.weatherapp.registration.domain.usecase.Register
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
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideSharedPref(app: Application): SharedPref {
        return SharedPref(context = app)
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}