package com.cesarschool.android_weather.data.repository

import com.cesarschool.android_weather.data.local.model.Favorite

interface FavoriteRepository {
    fun save(favorite: Favorite)
    fun update(favorite: Favorite)
    fun remove(favorite: Favorite)
    fun getAll(): List<Favorite>
    fun favoriteById(id: Long): Favorite?
    fun getAllFiltered(q: String): List<Favorite>?
}