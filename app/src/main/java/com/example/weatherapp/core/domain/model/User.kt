package com.example.weatherapp.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(val name: String, @PrimaryKey val email: String, val password: String)