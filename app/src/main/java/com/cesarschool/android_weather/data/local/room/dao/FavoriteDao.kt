package com.cesarschool.android_weather.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cesarschool.android_weather.data.local.COLUMN_ID
import com.cesarschool.android_weather.data.local.COLUMN_NAME
import com.cesarschool.android_weather.data.local.COLUMN_NAME_COUNTRY
import com.cesarschool.android_weather.data.local.TABLE_FAVORITE
import com.cesarschool.android_weather.data.local.model.Favorite

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: Favorite): Long

    @Update
    fun update(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("SELECT * FROM $TABLE_FAVORITE")
    fun getAll(): List<Favorite>

    @Query("SELECT * FROM $TABLE_FAVORITE WHERE $COLUMN_ID = :id")
    fun favoriteById(id: Long): Favorite?

    @Query("SELECT * FROM $TABLE_FAVORITE WHERE ($COLUMN_NAME LIKE '%' || :term || '%' OR $COLUMN_NAME_COUNTRY LIKE '%' || :term || '%')")
    fun getAllFiltered(term: String): List<Favorite>?

}