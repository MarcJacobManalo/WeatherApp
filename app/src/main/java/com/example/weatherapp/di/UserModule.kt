package com.example.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.core.data.repository.UserRepositoryImpl
import com.example.weatherapp.core.data.source.SharedPref
import com.example.weatherapp.core.data.source.UserDatabase
import com.example.weatherapp.core.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Singleton
    @Provides
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(app, UserDatabase::class.java, UserDatabase.DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserRepository(db: UserDatabase, sharedPref: SharedPref): UserRepository {
        return UserRepositoryImpl(dao = db.userDao, sharedPref = sharedPref)
    }
}