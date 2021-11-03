package com.venicio.sagamarvel.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import com.venicio.sagamarvel.data.db.dao.MovieDao
import com.venicio.sagamarvel.data.db.model.FavoriteEntity
import com.venicio.sagamarvel.data.model.Movies
import kotlinx.coroutines.flow.Flow

class DataSourceLocal(
    private val movieDao: MovieDao
) {

    suspend fun insert(movie: Movies) {
        return movieDao.insert(movie)
    }

    fun fetchAllMoviesDb(): Flow<List<Movies>> {
        return movieDao.getAll()
    }

    fun filterName(): LiveData<List<Movies>> = movieDao.filterByName()

    fun filterYear(): LiveData<List<Movies>> = movieDao.filterByYear()

    fun searchFromDatabase(searchQuery: String): LiveData<List<Movies>>{
        return  movieDao.searchFromDatabase(searchQuery)
    }

    suspend fun insertFavorite(movieFavorite: FavoriteEntity) {
        return movieDao.insertFavorite(movieFavorite)
    }

    fun findAllFavorites(): Flow<List<FavoriteEntity>> {
        return movieDao.getFavoriteAll()
    }

    suspend fun removeFavorite(titleFavorite: String) {
        return movieDao.removeFavorite(titleFavorite)
    }

}