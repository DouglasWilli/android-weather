package com.cesarschool.android_weather.util

class IconUtils {
    companion object {
        fun getWeatherIconUrl(icon: String): String {
            return "http://openweathermap.org/img/wn/${icon}@4x.png"
        }
    }
}