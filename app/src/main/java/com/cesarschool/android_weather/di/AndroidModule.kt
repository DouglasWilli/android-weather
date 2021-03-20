package com.cesarschool.android_weather.di

import com.cesarschool.android_weather.data.local.room.WeatherDatabase
import com.cesarschool.android_weather.data.local.room.repository.FavoriteRoomRepository
import com.cesarschool.android_weather.data.repository.FavoriteRepository
import com.cesarschool.android_weather.ui.favorite.FavoriteViewModel
import com.cesarschool.android_weather.ui.forecast.ForecastViewModel
import com.cesarschool.android_weather.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel

import org.koin.dsl.module.module

val androidModule = module {

    single {
        this
    }

    single {
        FavoriteRoomRepository(WeatherDatabase.getInstance(context = get())) as FavoriteRepository
    }

    viewModel {
        SearchViewModel(context = get())
    }

    viewModel {
        ForecastViewModel(context = get())
    }

    viewModel {
        FavoriteViewModel(context = get())
    }
}