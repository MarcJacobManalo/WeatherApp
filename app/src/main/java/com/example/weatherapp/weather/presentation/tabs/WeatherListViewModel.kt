package com.example.weatherapp.weather.presentation.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.domain.usecase.GetLastKnownLocation
import com.example.weatherapp.core.presentation.BaseLocationViewModel
import com.example.weatherapp.core.presentation.BaseViewModel
import com.example.weatherapp.weather.domain.model.Weather
import com.example.weatherapp.weather.domain.usecase.FetchWeathers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ph.easethetics.easentralized.easelife.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val getLastKnownLocationUseCase: GetLastKnownLocation,
    private val fetchWeathersUseCase: FetchWeathers
) : BaseLocationViewModel(getLastKnownLocationUseCase) {

    private val _weathers = MutableLiveData<List<Weather>>()
    val weathers: LiveData<List<Weather>> get() = _weathers

    private val _message = SingleLiveEvent<String>()
    override val message: LiveData<String> get() = _message

    fun getLastKnownLocation() {
        viewModelScope.launch {
            getLastKnownLocation(
                onError = { e ->
                    withContext(Dispatchers.Main) {
                        _message.value = e.message
                    }
                }, onSuccess = { location ->
                    fetchWeathers(location.latitude, location.longitude)
                })
        }
    }

    private fun fetchWeathers(lat: Double, lon: Double) {
        viewModelScope.launch {
            fetchWeathersUseCase.execute(lat = lat, lon = lon).catch { e ->
                withContext(Dispatchers.Main) {
                    _message.value = e.message
                }
            }.collect { weathers ->
                withContext(Dispatchers.Main) {
                    _weathers.value = weathers ?: listOf()
                }
            }
        }
    }
}