package com.example.weatherapp.weather.presentation.tabs

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.R
import com.example.weatherapp.core.util.extension.coordinatesToCityAndCountry
import com.example.weatherapp.databinding.FragmentCurrentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {

    companion object {
        const val TITLE = "Current Weather"
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val _viewModel: CurrentWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()

        checkLocationServices()
    }

    private fun initObservables() {
        _viewModel.weatherIcon.observe(viewLifecycleOwner) {
            val weatherIcon = when (it) {
                WeatherIcon.SUN -> R.drawable.ic_sunny
                WeatherIcon.RAIN
                -> R.drawable.ic_rainy
                WeatherIcon.MOON
                -> R.drawable.ic_moon
                else -> 0
            }
            _binding?.ivWeather?.setImageResource(weatherIcon)
        }

        _viewModel.location.observe(viewLifecycleOwner) {
            _binding?.txtLocation?.text =
                requireContext().coordinatesToCityAndCountry(it.latitude, it.longitude)
        }

        _viewModel.temperatureInCelsius.observe(viewLifecycleOwner) {
            _binding?.txtTemperature?.text = it
        }

        _viewModel.sunrise.observe(viewLifecycleOwner) {
            _binding?.txtSunrise?.text = it
        }

        _viewModel.sunset.observe(viewLifecycleOwner) {
            _binding?.txtSunset?.text = it
        }

        _viewModel.message.observe(viewLifecycleOwner) {
            toastMessage(it)
        }
    }

    private fun checkLocationServices(){
        val locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            requestLocationPermission()
        } else {
            toastMessage("Please enable location services and restart")
        }
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            _viewModel.getLastKnownLocation()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            _viewModel.getLastKnownLocation()
        } else {
            toastMessage("Location permission is not granted")
        }
    }

    private fun toastMessage(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main) {
                Toast.makeText(context?.applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}