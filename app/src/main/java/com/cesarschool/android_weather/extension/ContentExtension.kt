package com.cesarschool.android_weather.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

fun Context.isInternetAvailable(): Boolean {
    var result = false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        cm.getNetworkCapabilities(cm.activeNetwork)?.run {
            result = when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
    }

    return result
}