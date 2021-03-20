package com.cesarschool.android_weather

import android.app.Application
import com.cesarschool.android_weather.di.androidModule
import org.koin.android.ext.android.startKoin
import org.koin.standalone.StandAloneContext.stopKoin

class AppWeather : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(androidModule))
    }
    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}