package com.example.weatherapp.di

import com.example.weatherapp.core.domain.repository.UserRepository
import com.example.weatherapp.login.domain.usecase.FetchCurrentUser
import com.example.weatherapp.login.domain.usecase.Login
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun provideLogin(repository: UserRepository): Login {
        return Login(repository = repository)
    }

    @Singleton
    @Provides
    fun provideCheckCurrentUser(repository: UserRepository): FetchCurrentUser {
        return FetchCurrentUser(repository = repository)
    }
}