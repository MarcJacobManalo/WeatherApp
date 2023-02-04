package com.example.weatherapp.core.data.repository

import com.example.weatherapp.core.data.source.TimeSource
import com.example.weatherapp.core.domain.repository.TimeRepository
import java.util.*
import javax.inject.Inject

class TimeRepositoryImpl @Inject constructor(private val source: TimeSource) : TimeRepository {
    override fun getCurrentTime(): Date {
        return source.getCurrentTime()
    }
}