package com.cesarschool.android_weather.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cesarschool.android_weather.data.local.DATABASE_NAME
import com.cesarschool.android_weather.data.local.DATABASE_VERSION
import com.cesarschool.android_weather.data.local.model.Favorite
import com.cesarschool.android_weather.data.local.room.dao.FavoriteDao

@Database(entities = [Favorite::class], version = DATABASE_VERSION)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

    companion object {
        private var instance: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase {
            if (instance == null) {
                synchronized(WeatherDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherDatabase::class.java,
                        DATABASE_NAME
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }
    }
}