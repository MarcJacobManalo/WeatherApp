package com.example.weatherapp.weather.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.weather.presentation.tabs.CurrentWeatherFragment
import com.example.weatherapp.weather.presentation.tabs.WeatherListFragment
import com.google.android.material.tabs.TabLayoutMediator

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.tabContent?.apply {
            adapter = TabsViewPagerAdapter(this@WeatherFragment)
            offscreenPageLimit = 2
        }
        _binding?.let {
            TabLayoutMediator(it.tabs, it.tabContent) { tab, position ->
                tab.text = when(position){
                    0 -> CurrentWeatherFragment.TITLE
                    1 -> WeatherListFragment.TITLE
                    else -> throw IllegalArgumentException()
                }
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}