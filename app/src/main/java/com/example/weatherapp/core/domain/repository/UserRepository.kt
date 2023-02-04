package com.example.weatherapp.core.domain.repository

import com.example.weatherapp.core.domain.model.User

interface UserRepository {
    suspend fun fetchUserByEmail(email: String): User?
    suspend fun saveUser(user: User)
    fun getCurrentUser(): User?
    fun saveCurrentUser(user: User)

}