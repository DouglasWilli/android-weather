package com.cesarschool.android_weather.data.local.room.repository

import com.cesarschool.android_weather.data.local.model.Favorite
import com.cesarschool.android_weather.data.local.room.WeatherDatabase
import com.cesarschool.android_weather.data.repository.FavoriteRepository

class FavoriteRoomRepository (
    database: WeatherDatabase
) : FavoriteRepository {
    private val favoriteDao = database.getFavoriteDao()

    override fun save(favorite: Favorite) {
        if(favorite.id == 0L) {
            val id = favoriteDao.insert(favorite)
            favorite.id = id
        } else {
            favoriteDao.update(favorite)
        }
    }

    override fun update(favorite: Favorite) {
        favoriteDao.update(favorite)
    }

    override fun remove(favorite: Favorite) {
        favoriteDao.delete(favorite)
    }

    override fun getAll(): List<Favorite> {
        return favoriteDao.getAll()
    }

    override fun favoriteById(id: Long): Favorite? {
        return favoriteDao.favoriteById(id)
    }

    override fun getAllFiltered(q: String): List<Favorite>? {
        return favoriteDao.getAllFiltered(q)
    }
}