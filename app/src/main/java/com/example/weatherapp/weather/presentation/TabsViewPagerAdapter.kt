package com.example.weatherapp.weather.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherapp.weather.presentation.tabs.CurrentWeatherFragment
import com.example.weatherapp.weather.presentation.tabs.WeatherListFragment

class TabsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CurrentWeatherFragment()
            1 -> WeatherListFragment()
            else -> throw IllegalArgumentException()
        }
    }
}