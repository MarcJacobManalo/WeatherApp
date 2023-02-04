package com.example.weatherapp.registration.domain.usecase

import com.example.weatherapp.core.domain.model.User
import com.example.weatherapp.core.domain.repository.UserRepository
import com.example.weatherapp.login.domain.exception.InvalidUserInput
import com.example.weatherapp.registration.domain.exception.EmailNotAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Register @Inject constructor(private val repository: UserRepository) {

    suspend fun execute(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Unit> {
        return flow {
            if (name.trim().isBlank()) {
                throw InvalidUserInput("Please enter your name")
            } else if (email.trim().isBlank()) {
                throw InvalidUserInput("Please enter your email")
            } else if (password.trim().isBlank()) {
                throw InvalidUserInput("Please enter your password")
            } else if (confirmPassword.trim().isBlank()) {
                throw InvalidUserInput("Please confirm your password")
            } else if (password.trim() != confirmPassword.trim()) {
                throw InvalidUserInput("Password does not match")
            } else if (repository.fetchUserByEmail(email) != null) {
                throw EmailNotAvailable()
            } else {
                val newUser = User(name = name, email = email, password = password)
                repository.saveCurrentUser(newUser)
               emit(repository.saveUser(newUser))
            }
        }.flowOn(Dispatchers.IO)
    }
}