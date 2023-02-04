package com.example.weatherapp.core.data.repository

import com.example.weatherapp.core.data.source.LocationSource
import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val locationSource: LocationSource) :
    LocationRepository {

    override suspend fun getLastKnownLocation(): Location {
        return locationSource.getLastKnownLocation()
    }
}