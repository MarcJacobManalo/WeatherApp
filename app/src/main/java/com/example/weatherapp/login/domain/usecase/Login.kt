package com.example.weatherapp.login.domain.usecase

import com.example.weatherapp.core.domain.model.User
import com.example.weatherapp.core.domain.repository.UserRepository
import com.example.weatherapp.login.domain.exception.InvalidUserInput
import com.example.weatherapp.login.domain.exception.NoUserFound
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Login @Inject constructor(private val repository: UserRepository) {

    suspend fun execute(email: String, password: String): Flow<Unit> {
        return flow {
            val user = repository.fetchUserByEmail(email = email)
            if (email.trim().isBlank()) {
                throw InvalidUserInput("Please enter your email")
            } else if (password.trim().isBlank()) {
                throw InvalidUserInput("Please enter your password")
            } else if (user == null || user.password != password) {
                throw NoUserFound()
            } else {
                emit(repository.saveCurrentUser(user))
            }
        }.flowOn(Dispatchers.IO)
    }
}