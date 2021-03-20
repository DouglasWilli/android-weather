package com.cesarschool.android_weather.data.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityRaw(
    val id: Long,
    val name: String,
    val country: String,
    val tempAmount: Double,
    val tempUnit: String,
    val tempIcon: String
): Parcelable