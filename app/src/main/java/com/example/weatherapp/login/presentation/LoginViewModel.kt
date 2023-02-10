package com.example.weatherapp.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.presentation.BaseViewModel
import com.example.weatherapp.login.domain.usecase.IsAuthenticatedUser
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
    private val isAuthenticatedUserUseCase: IsAuthenticatedUser,
    private val loginUseCase: Login
) : BaseViewModel() {

    private val _isAuthenticated = SingleLiveEvent<Boolean>()
    val isAuthenticated: LiveData<Boolean> get() = _isAuthenticated

    private val _message = SingleLiveEvent<String>()
    override val message: LiveData<String> get() = _message

    fun checkAuthentication() {
        _isAuthenticated.value = isAuthenticatedUserUseCase.execute()
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