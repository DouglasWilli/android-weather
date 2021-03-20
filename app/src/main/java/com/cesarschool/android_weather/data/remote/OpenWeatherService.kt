package com.cesarschool.android_weather.data.remote

import com.cesarschool.android_weather.data.remote.model.FindResult
import com.cesarschool.android_weather.data.remote.model.ForecastResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    @GET("/data/2.5/find")
    fun findCity(
        @Query("q")
        cityName: String,

        @Query("units")
        units: String,

        @Query("lang")
        lang: String,

        @Query("appid")
        appId: String
    ): Call<FindResult>

    @GET("/data/2.5/forecast")
    fun listForecasts(
        @Query("id")
        cityId: Long,

        @Query("units")
        units: String,

        @Query("lang")
        lang: String,

        @Query("appid")
        appId: String
    ): Call<ForecastResult>

}