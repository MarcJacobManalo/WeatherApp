package com.example.weatherapp.di

import com.example.weatherapp.core.data.repository.TimeRepositoryImpl
import com.example.weatherapp.core.data.source.TimeSource
import com.example.weatherapp.core.domain.repository.TimeRepository
import com.example.weatherapp.core.domain.usecase.IsCurrentTimePassed6pm
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimeModule {

    @Singleton
    @Provides
    fun provideTimeSource(): TimeSource {
        return TimeSource()
    }

    @Singleton
    @Provides
    fun provideTimeRepository(source: TimeSource): TimeRepository {
        return TimeRepositoryImpl(source = source)
    }

    @Singleton
    @Provides
    fun provideIsCurrentTimePassed6pm(repository: TimeRepository): IsCurrentTimePassed6pm {
        return IsCurrentTimePassed6pm(repository = repository)
    }
}