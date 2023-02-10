package com.example.weatherapp.core.presentation

import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.usecase.GetLastKnownLocation
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch

abstract class BaseLocationViewModel(
    private val getLastKnownLocationUseCase: GetLastKnownLocation
) : BaseViewModel() {

    suspend fun getLastKnownLocation(
        onError: suspend FlowCollector<Location>.(Throwable) -> Unit,
        onSuccess: suspend (Location) -> Unit
    ) {
        getLastKnownLocationUseCase.execute().catch(onError).collect(onSuccess)
    }
}