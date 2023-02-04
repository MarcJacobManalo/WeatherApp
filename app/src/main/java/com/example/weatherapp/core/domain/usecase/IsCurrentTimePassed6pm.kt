package com.example.weatherapp.core.domain.usecase

import com.example.weatherapp.core.domain.repository.TimeRepository
import java.util.*
import javax.inject.Inject

class IsCurrentTimePassed6pm @Inject constructor(private val repository: TimeRepository) {

    fun execute(): Boolean {
        val currentTime = repository.getCurrentTime()
        val evening = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 18)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time
        return currentTime.after(evening)
    }
}