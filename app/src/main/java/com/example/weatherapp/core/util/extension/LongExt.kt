package com.example.weatherapp.core.util.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertUnixTimeToLocalTime(): String {
    val date = Date(this * 1000)
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    formatter.timeZone = TimeZone.getDefault()
    return formatter.format(date)
}

fun Long.convertUnixTimeToDayOfWeek(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this * 1000
    return when (calendar[Calendar.DAY_OF_WEEK]) {
        Calendar.SUNDAY -> "Sunday"
        Calendar.MONDAY -> "Monday"
        Calendar.TUESDAY -> "Tuesday"
        Calendar.WEDNESDAY -> "Wednesday"
        Calendar.THURSDAY -> "Thursday"
        Calendar.FRIDAY -> "Friday"
        Calendar.SATURDAY -> "Saturday"
        else -> ""
    }
}