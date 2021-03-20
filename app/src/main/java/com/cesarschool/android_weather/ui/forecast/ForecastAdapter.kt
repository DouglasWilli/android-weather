package com.cesarschool.android_weather.ui.forecast

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cesarschool.android_weather.R
import com.cesarschool.android_weather.data.remote.model.Forecast
import com.cesarschool.android_weather.databinding.ItemCityForecastBinding
import com.cesarschool.android_weather.preferences.SharedPreferences
import com.cesarschool.android_weather.util.DateTimePresenter
import com.cesarschool.android_weather.util.IconUtils

class ForecastAdapter(
    private val context: Context
) : ListAdapter<Forecast, ForecastAdapter.ViewHolder>(ForecastDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCityForecastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemCityForecastBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(forecast: Forecast) {
            binding.apply {
                val lang = SharedPreferences.getLangKey(context)
                tvForecastDate.text = DateTimePresenter.present(forecast.dateTime, lang)
                ivWeatherIcon.load(IconUtils.getWeatherIconUrl(forecast.weathers[0].icon)) {
                    crossfade(true)
                    placeholder(R.drawable.ic_weather_placeholder)
                }
                tvWeatherCondition.text = forecast.weathers[0].description
                tvTempAmount.text = forecast.main.temperature.toString()
                tvTempUnit.text = SharedPreferences.getTempUnitSearched()
                tvCloudPercentage.text = forecast.clouds.percentage.toString()
                tvWindSpeed.text = forecast.wind.speed.toString()
            }
        }
    }
}