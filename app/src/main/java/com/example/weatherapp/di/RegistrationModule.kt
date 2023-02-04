package com.example.weatherapp.di

import com.example.weatherapp.core.domain.repository.UserRepository
import com.example.weatherapp.registration.domain.usecase.Register
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegistrationModule {

    @Singleton
    @Provides
    fun provideRegister(repository: UserRepository): Register {
        return Register(repository = repository)
    }
}