package com.example.weatherapp.core.presentation.revamp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.login.presentation.revamp.LoginScreen

sealed class Screen(val route: String) {
    companion object {
        private val LOGIN_SCREEN = "login_screen"
        private val REGISTRATION_SCREEN = "registration_screen"
        private val WEATHER_SCREEN = "weather_screen"
    }
    data object LoginScreen: Screen(LOGIN_SCREEN)
    data object RegistrationScreen: Screen(REGISTRATION_SCREEN)
    data object WeatherScreen: Screen(WEATHER_SCREEN)

}
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route){ LoginScreen() }
    }
}
