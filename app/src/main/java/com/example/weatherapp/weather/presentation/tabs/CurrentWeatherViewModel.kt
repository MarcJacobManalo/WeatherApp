package com.example.weatherapp.weather.presentation.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.usecase.GetLastKnownLocation
import com.example.weatherapp.core.domain.usecase.IsCurrentTimePassed6pm
import com.example.weatherapp.weather.domain.model.WeatherDescription
import com.example.weatherapp.weather.domain.usecase.FetchCurrentWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ph.easethetics.easentralized.easelife.util.SingleLiveEvent
import javax.inject.Inject

enum class WeatherIcon {
    SUN, RAIN, MOON
}

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val fetchCurrentWeatherUseCase: FetchCurrentWeather,
    private val isCurrentTimePassed6pmUseCase: IsCurrentTimePassed6pm,
    private val getLastKnownLocationUseCase: GetLastKnownLocation,
) : ViewModel() {

    private val _weatherIcon = MutableLiveData<WeatherIcon>()
    val weatherIcon: LiveData<WeatherIcon> get() = _weatherIcon

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> get() = _location

    private val _temperatureInCelsius = MutableLiveData<String>()
    val temperatureInCelsius: LiveData<String> get() = _temperatureInCelsius

    private val _sunrise = MutableLiveData<String>()
    val sunrise: LiveData<String> get() = _sunrise

    private val _sunset = MutableLiveData<String>()
    val sunset: LiveData<String> get() = _sunset

    private val _message = SingleLiveEvent<String>()
    val message: LiveData<String> get() = _message

    fun getLastKnownLocation() {
        viewModelScope.launch {
            getLastKnownLocationUseCase.execute().catch { e ->
                withContext(Dispatchers.Main) {
                    _message.value = e.message
                }
            }.collect { location ->
                withContext(Dispatchers.Main) {
                    _location.value = location
                }
                fetchCurrentWeather(location.latitude, location.longitude)
            }
        }
    }

    private fun fetchCurrentWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            fetchCurrentWeatherUseCase.execute(lat = lat, lon = lon).catch { e ->
                withContext(Dispatchers.Main) {
                    _message.value = e.message
                }
            }.collect { weather ->
                weather?.let {
                    _temperatureInCelsius.value = "${weather.temperature.toString()}°C"
                    _sunrise.value = "Sunrise: ${weather.getSunriseDateTime()}"
                    _sunset.value = "Sunset: ${weather.getSunsetDateTime()}"
                    if (isCurrentTimePassed6pmUseCase.execute()) {
                        withContext(Dispatchers.Main) {
                            _weatherIcon.value = WeatherIcon.MOON
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            when (weather.getDescription()) {
                                WeatherDescription.SUNNY -> _weatherIcon.value = WeatherIcon.SUN
                                WeatherDescription.RAINY -> _weatherIcon.value = WeatherIcon.RAIN
                                else -> { /** do nothing **/}
                            }
                        }
                    }
                }
            }
        }
    }
}