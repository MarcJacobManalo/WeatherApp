package com.example.weatherapp.registration.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.registration.domain.usecase.Register
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ph.easethetics.easentralized.easelife.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val registerUseCase: Register) :
    ViewModel() {

    private val _isRegistered = SingleLiveEvent<Boolean>()
    val isRegistered: LiveData<Boolean> get() = _isRegistered

    private val _message = SingleLiveEvent<String>()
    val message: LiveData<String> get() = _message

    fun register(name: String, email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            registerUseCase.execute(
                name = name,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            ).catch { e ->
                withContext(Dispatchers.Main) {
                    _message.value = e.message
                }
            }.collect {
                withContext(Dispatchers.Main) {
                    _isRegistered.value = true
                }
            }
        }
    }
}