package com.example.weatherapp.core.data.source

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.example.weatherapp.core.data.exception.LocationNotFound
import com.example.weatherapp.core.data.exception.LocationPermissionException
import com.example.weatherapp.core.domain.model.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Tasks
import javax.inject.Inject

class LocationSource @Inject constructor(private val context: Context) {

    fun getLastKnownLocation(): Location {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            throw LocationPermissionException()
        }
        try {
            val fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(context)
            val task = fusedLocationProviderClient.lastLocation
            val location = Tasks.await(task)
            if (location != null) {
                return Location(location.latitude, location.longitude)
            } else {
                throw LocationNotFound()
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}