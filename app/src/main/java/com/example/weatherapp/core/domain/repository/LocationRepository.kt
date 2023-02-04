package com.example.weatherapp.core.domain.repository

import com.example.weatherapp.core.domain.model.Location

interface LocationRepository {
    suspend fun getLastKnownLocation() : Location
}