package com.cesarschool.android_weather.ui.forecast

import androidx.recyclerview.widget.DiffUtil
import com.cesarschool.android_weather.data.remote.model.Forecast

class ForecastDiff : DiffUtil.ItemCallback<Forecast>() {
    override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast) = oldItem == newItem
}