package com.venicio.sagamarvel.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.venicio.sagamarvel.data.db.dao.MovieDao
import com.venicio.sagamarvel.data.db.model.FavoriteEntity
import com.venicio.sagamarvel.data.model.Movies

class DataSourceLocal(
    private val movieDao: MovieDao
) {

    suspend fun insert(movie: Movies) : Long{
      return  movieDao.insert(movie)
    }

     fun fetchAllMoviesDb(): LiveData<List<Movies>> {
      return movieDao.getAll()
    }

    fun filterName() : LiveData<List<Movies>> = movieDao.filterByName()

    fun filterYear() : LiveData<List<Movies>> = movieDao.filterByYear()

    suspend fun insertFavorite(movieFavorite: FavoriteEntity) {
        Log.d(" INSERT DATA", "insertFavorite: DATASOURCE ")
        return movieDao.insertFavorite(movieFavorite)

    }

    suspend fun findAllFavorites() : MutableList<Movies> {
        return movieDao.getFavoriteAll()
    }

    suspend fun removeFavorite(titleFavorite: String) {
        return movieDao.removeFavorite (titleFavorite)
    }

}