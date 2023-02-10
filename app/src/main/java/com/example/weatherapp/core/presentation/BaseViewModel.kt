package com.example.weatherapp.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract val message: LiveData<String>
}