package com.example.weatherapp.core.data.source

import java.util.*

class TimeSource {
    fun getCurrentTime(): Date {
        return Calendar.getInstance().time
    }
}