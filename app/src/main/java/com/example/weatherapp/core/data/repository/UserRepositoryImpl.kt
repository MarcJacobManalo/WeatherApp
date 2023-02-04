package com.example.weatherapp.core.data.repository

import com.example.weatherapp.core.data.source.SharedPref
import com.example.weatherapp.core.data.source.UserDao
import com.example.weatherapp.core.domain.model.User
import com.example.weatherapp.core.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val sharedPref: SharedPref
) : UserRepository {

    override suspend fun fetchUserByEmail(email: String): User? {
        return dao.getUserByEmail(email)
    }

    override suspend fun saveUser(user: User) {
        dao.insertUser(user)
    }

    override fun getCurrentUser(): User? {
        return sharedPref.getUser()
    }

    override fun saveCurrentUser(user: User) {
        sharedPref.saveUser(user)
    }
}