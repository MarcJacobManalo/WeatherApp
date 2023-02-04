package com.example.weatherapp.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.login.domain.usecase.FetchCurrentUser
import com.example.weatherapp.login.domain.usecase.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ph.easethetics.easentralized.easelife.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchCurrentUserUseCase: FetchCurrentUser,
    private val loginUseCase: Login
) : ViewModel() {

    private val _isAuthenticated = SingleLiveEvent<Boolean>()
    val isAuthenticated: LiveData<Boolean> get() = _isAuthenticated

    private val _message = SingleLiveEvent<String>()
    val message: LiveData<String> get() = _message

    fun checkAuthentication() {
        val user = fetchCurrentUserUseCase.execute()
        _isAuthenticated.value = user != null
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.execute(email = email, password = password).catch { e ->
                withContext(Dispatchers.Main) {
                    _message.value = e.message
                }
            }.collect {
                withContext(Dispatchers.Main) {
                    _isAuthenticated.value = true
                }
            }
        }
    }
}