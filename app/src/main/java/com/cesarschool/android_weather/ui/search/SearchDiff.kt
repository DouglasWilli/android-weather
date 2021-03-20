package com.cesarschool.android_weather.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.cesarschool.android_weather.data.remote.model.City

class SearchDiff : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City) = oldItem == newItem
    override fun areContentsTheSame(oldItem: City, newItem: City) = oldItem == newItem
}