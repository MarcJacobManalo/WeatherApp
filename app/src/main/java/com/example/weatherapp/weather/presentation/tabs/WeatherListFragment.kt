package com.example.weatherapp.weather.presentation.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.FragmentWeatherListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class WeatherListFragment : Fragment() {

    companion object {
        const val TITLE = "All Weathers"
    }

    private var _binding: FragmentWeatherListBinding? = null
    private val _viewModel: WeatherListViewModel by activityViewModels()

    private lateinit var _weatherForecastsAdapter: WeatherForecastsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservables()

        _viewModel.getLastKnownLocation()

        initWeatherForecastsList()
    }

    private fun initObservables() {
        _viewModel.weathers.observe(viewLifecycleOwner) { weathers ->
            if (::_weatherForecastsAdapter.isInitialized) {
                _weatherForecastsAdapter.updateList(weathers)
            }
        }
        _viewModel.message.observe(viewLifecycleOwner) { message ->
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context?.applicationContext, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initWeatherForecastsList() {
        _weatherForecastsAdapter = WeatherForecastsAdapter()

        _binding?.rvWeatherForecasts?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = _weatherForecastsAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}