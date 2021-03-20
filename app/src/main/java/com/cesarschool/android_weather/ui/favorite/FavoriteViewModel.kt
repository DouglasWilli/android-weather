package com.cesarschool.android_weather.ui.favorite

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.cesarschool.android_weather.R
import com.cesarschool.android_weather.data.local.model.Favorite
import com.cesarschool.android_weather.data.local.room.WeatherDatabase

class FavoriteViewModel(
    private val context: Context
) : ViewModel() {

    fun searchFavorits(trem: String, favoriteAdapter: FavoriteAdapter) {
        val dao = WeatherDatabase.getInstance(context).getFavoriteDao()
        favoriteAdapter.submitList(dao.getAllFiltered(trem))
    }

    fun unfavorite(favorite: Favorite, favoriteAdapter: FavoriteAdapter) {
        val dao = WeatherDatabase.getInstance(context).getFavoriteDao()
        dao.delete(favorite)
        favoriteAdapter.submitList(dao.getAll())
        Toast.makeText(context, context.resources.getString(R.string.text_un_favorited), Toast.LENGTH_SHORT).show()
    }
}