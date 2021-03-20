package com.cesarschool.android_weather.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cesarschool.android_weather.data.local.TABLE_FAVORITE

@Entity(tableName = TABLE_FAVORITE)
data class Favorite (
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "city_name") var cityName: String,
    @ColumnInfo(name = "city_country") var cityCountry: String
)