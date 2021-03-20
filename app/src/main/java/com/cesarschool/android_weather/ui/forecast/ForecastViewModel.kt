package com.cesarschool.android_weather.ui.forecast

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.cesarschool.android_weather.R
import com.cesarschool.android_weather.data.local.model.CityRaw
import com.cesarschool.android_weather.data.local.model.Favorite
import com.cesarschool.android_weather.data.local.room.WeatherDatabase
import com.cesarschool.android_weather.data.remote.RetrofitManager
import com.cesarschool.android_weather.data.remote.model.ForecastResult
import com.cesarschool.android_weather.databinding.ActivityForecastBinding
import com.cesarschool.android_weather.extension.isInternetAvailable
import com.cesarschool.android_weather.preferences.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel(
    private val context: Context
) : ViewModel() {

}