package com.example.weatherapp.login.domain.usecase

import com.example.weatherapp.core.domain.repository.UserRepository
import javax.inject.Inject

class IsAuthenticatedUser @Inject constructor (private val repository: UserRepository) {

    fun execute() : Boolean {
        return repository.getCurrentUser() != null
    }
}