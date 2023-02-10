package com.example.weatherapp.core.util.extension

import android.content.Context
import android.location.Geocoder
import java.util.*

fun Context.coordinatesToCityAndCountry(lat: Double, lon: Double): String {

    val geocoder = Geocoder(this, Locale.getDefault())
    val addresses = geocoder.getFromLocation(lat, lon, 1)

    return if (addresses?.isNotEmpty() == true) {
        "${addresses.first().locality}, ${addresses.first().countryName}"
    } else {
        ""
    }
}