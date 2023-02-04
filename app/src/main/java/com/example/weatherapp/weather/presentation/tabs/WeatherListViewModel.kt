package com.example.weatherapp.weather.presentation.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class WeatherListViewModel @Inject constructor(private val fetchWeathersUseCase: FetchWeathers) :
    ViewModel() {

    private val _weathers = MutableLiveData<List<Weather>>()
    val weathers: LiveData<List<Weather>> get() = _weathers

    private val _message = SingleLiveEvent<String>()
    val message: LiveData<String> get() = _message

    fun fetchWeathers() {
        viewModelScope.launch {
            fetchWeathersUseCase.execute().catch { }.collect { weathers ->
                withContext(Dispatchers.Main) {
                    _weathers.value = weathers ?: listOf()
                }
            }
        }
    }
}