package com.cesarschool.android_weather.ui.search

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.cesarschool.android_weather.data.remote.RetrofitManager
import com.cesarschool.android_weather.data.remote.model.FindResult
import com.cesarschool.android_weather.extension.isInternetAvailable
import com.cesarschool.android_weather.preferences.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(
    private val context: Context
) : ViewModel() {

    fun findCity(edtSearch: String, searchAdapter: SearchAdapter) {
        val unit = SharedPreferences.getUnitKey(context)
        val lang = SharedPreferences.getLangKey(context)

        if (context.isInternetAvailable()) {
            val call = RetrofitManager.getOpenWeatherService().findCity(
                edtSearch,
                unit,
                lang,
                ""
            )

            call.enqueue(object : Callback<FindResult> {
                override fun onResponse(call: Call<FindResult>, response: Response<FindResult>) {
                    if (response.isSuccessful) {
                        searchAdapter.submitList(response.body()?.cities)
                    } else {
                        Log.w("SearchViewModel", "onResponse: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<FindResult>, t: Throwable) {
                    Log.e("SearchViewModel", "onFailure", t)
                }
            })
        } else {
            Toast.makeText(context, "No network access", Toast.LENGTH_SHORT).show()
        }
    }
}