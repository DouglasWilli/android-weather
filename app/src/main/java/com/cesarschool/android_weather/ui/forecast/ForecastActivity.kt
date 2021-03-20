package com.cesarschool.android_weather.ui.forecast

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.cesarschool.android_weather.R
import com.cesarschool.android_weather.data.local.model.CityRaw
import com.cesarschool.android_weather.data.local.model.Favorite
import com.cesarschool.android_weather.data.local.room.WeatherDatabase
import com.cesarschool.android_weather.data.remote.RetrofitManager
import com.cesarschool.android_weather.data.remote.model.ForecastResult
import com.cesarschool.android_weather.databinding.ActivityForecastBinding
import com.cesarschool.android_weather.extension.isInternetAvailable
import com.cesarschool.android_weather.extension.toPx
import com.cesarschool.android_weather.preferences.SharedPreferences
import com.cesarschool.android_weather.util.IconUtils
import com.cesarschool.android_weather.util.MarginItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForecastBinding
    private val forecastchAdapter by lazy { ForecastAdapter(this) }
    private lateinit var cityRaw: CityRaw
    private val viewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<CityRaw>("cityRaw")?.let {
            cityRaw = it
        }
        initUi()
        updateToggleButton(checkFavorite() == null)
        listForecasts(cityRaw.id)
    }

    private fun initUi() {
        binding.apply {
            tvForecastTitle.text = this@ForecastActivity.resources.getString(
                R.string.forecast_title, cityRaw.name, cityRaw.country
            )
            ivForecastWeather.load(
                IconUtils.getWeatherIconUrl(cityRaw.tempIcon)
            ) {
                crossfade(true)
                placeholder(
                    R.drawable.ic_weather_placeholder
                )
            }
            tvForecastTempAmount.text = cityRaw.tempAmount.toString()
            tvForecastTempUnit.text = cityRaw.tempUnit

            btnFavorite.setOnClickListener {
                toggleFavorite()
            }

            rvForecasts.apply {
                layoutManager = LinearLayoutManager(this@ForecastActivity)
                adapter = forecastchAdapter
                addItemDecoration(MarginItemDecoration(16.toPx()))
            }
        }
    }

    fun toggleFavorite() {
        cityRaw.let {
            val dao = WeatherDatabase.getInstance(this).getFavoriteDao()
            val cityFavorited = dao.favoriteById(cityRaw.id)

            if (cityFavorited == null) {
                dao.insert(Favorite(it.id, it.name, it.country))
                Toast.makeText(
                    this,
                    this.resources.getString(R.string.text_favorited),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                dao.delete(cityFavorited)
                Toast.makeText(
                    this,
                    this.resources.getString(R.string.text_un_favorited),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun updateToggleButton(isFavorite: Boolean) {
        binding.btnFavorite.text = if (isFavorite) {
            this.resources.getString(R.string.text_favorite)
        } else {
            this.resources.getString(R.string.text_un_favorite)
        }
    }

    private fun checkFavorite(): Favorite? {
        val dao = WeatherDatabase.getInstance(this@ForecastActivity).getFavoriteDao()
        return dao.favoriteById(cityRaw.id)
    }

    fun listForecasts(cityId: Long) {
        if (this.isInternetAvailable()) {
            val call = RetrofitManager.getOpenWeatherService().listForecasts(
                cityId,
                SharedPreferences.getUnitKey(this),
                SharedPreferences.getLangKey(this),
                ""
            )
            call.enqueue(object : Callback<ForecastResult> {
                override fun onResponse(
                    call: Call<ForecastResult>,
                    response: Response<ForecastResult>
                ) {
                    if (response.isSuccessful) {
                        forecastchAdapter.submitList(response.body()?.forecast)
                    } else {
                        Log.w("TAG", "onResponse: ${response.message()} ")
                    }
                }
                override fun onFailure(call: Call<ForecastResult>, t: Throwable) {
                    Log.e("TAG", "onFailure: ", t)
                }
            })
        } else {
            Toast.makeText(this, "No network access", Toast.LENGTH_SHORT).show()
        }
    }
}