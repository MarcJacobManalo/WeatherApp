package com.example.weatherapp.core.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.core.domain.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}