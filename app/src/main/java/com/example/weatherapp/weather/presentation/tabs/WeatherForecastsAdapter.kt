package com.example.weatherapp.weather.presentation.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.weather.domain.model.Weather
import com.example.weatherapp.weather.domain.model.WeatherDescription

class WeatherForecastsAdapter : RecyclerView.Adapter<WeatherForecastsAdapter.ViewHolder>() {

    private var list = listOf<Weather>()

    fun updateList(newList: List<Weather>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_weather_forecast, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtDayOfWeek.text = list[position].getDayOfWeekOfForecastDate()
        val weatherIcon = when (list[position].getDescription()) {
            WeatherDescription.SUNNY -> R.drawable.ic_sunny
            WeatherDescription.RAINY
            -> R.drawable.ic_rainy
            else -> 0
        }
        holder.ivWeather?.setImageResource(weatherIcon)
        holder.txtTemperature.text = "${list[position].temperature.toString()}°C"
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtDayOfWeek = view.findViewById<TextView>(R.id.txt_day_of_week)
        val ivWeather = view.findViewById<ImageView>(R.id.iv_weather)
        val txtTemperature = view.findViewById<TextView>(R.id.txt_temperature)
    }
}