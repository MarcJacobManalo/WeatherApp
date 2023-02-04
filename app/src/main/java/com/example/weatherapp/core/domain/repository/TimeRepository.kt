package com.example.weatherapp.core.domain.repository

import java.util.Date

interface TimeRepository {
    fun getCurrentTime() : Date
}