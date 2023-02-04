package com.example.weatherapp.core.domain.usecase

import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLastKnownLocation @Inject constructor(private val repository: LocationRepository) {
    suspend fun execute(): Flow<Location> {
        return flow {
            emit(repository.getLastKnownLocation())
        }.flowOn(Dispatchers.IO)
    }
}