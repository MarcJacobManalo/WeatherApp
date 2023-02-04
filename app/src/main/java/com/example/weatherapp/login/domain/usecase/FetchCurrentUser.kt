package com.example.weatherapp.login.domain.usecase

import com.example.weatherapp.core.domain.model.User
import com.example.weatherapp.core.domain.repository.UserRepository
import javax.inject.Inject

class FetchCurrentUser @Inject constructor (private val repository: UserRepository) {

    fun execute() : User? {
        return repository.getCurrentUser()
    }
}