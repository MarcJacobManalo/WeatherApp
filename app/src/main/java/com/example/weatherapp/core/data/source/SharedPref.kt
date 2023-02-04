package com.example.weatherapp.core.data.source

import android.content.Context
import com.example.weatherapp.core.domain.model.User
import javax.inject.Inject

class SharedPref @Inject constructor(context: Context) {

    private val sharedPrefName = "user_preference"
    private val nameKey = "name"
    private val emailKey = "email"
    private val passwordKey = "password"

    private val sharedPreferences =
        context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
    private val sharedPreEditor = sharedPreferences.edit()

    fun saveUser(user: User) {
        sharedPreEditor.apply {
            putString(nameKey, user.name)
            putString(emailKey, user.email)
            putString(passwordKey, user.password)
        }.commit()
    }

    fun getUser(): User? {
        val name = sharedPreferences.getString(nameKey, null)
        val email = sharedPreferences.getString(emailKey, null)
        val password = sharedPreferences.getString(passwordKey, null)

        return if (name == null || email == null || password == null) {
            null
        } else {
            User(name, email, password)
        }
    }
}