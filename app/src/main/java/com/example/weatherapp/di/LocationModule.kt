package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.core.data.repository.LocationRepositoryImpl
import com.example.weatherapp.core.data.source.LocationSource
import com.example.weatherapp.core.domain.repository.LocationRepository
import com.example.weatherapp.core.domain.usecase.GetLastKnownLocation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Singleton
    @Provides
    fun provideLocationSource(app: Application): LocationSource {
        return LocationSource(context = app)
    }

    @Singleton
    @Provides
    fun provideLocationRepository(source: LocationSource): LocationRepository {
        return LocationRepositoryImpl(locationSource = source)
    }

    @Singleton
    @Provides
    fun provideGetLastKnownLocation(repository: LocationRepository): GetLastKnownLocation {
        return GetLastKnownLocation(repository = repository)
    }
}