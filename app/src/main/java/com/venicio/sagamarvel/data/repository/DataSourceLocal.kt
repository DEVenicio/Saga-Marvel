package com.venicio.sagamarvel.data.repository

import androidx.lifecycle.LiveData
import com.venicio.sagamarvel.data.db.dao.MovieDao
import com.venicio.sagamarvel.data.model.Movies

class DataSourceLocal(
    private val movieDao: MovieDao
) {

    suspend fun insert(movie: Movies){
      return  movieDao.insert(movie)
    }

     fun fetchAllMoviesDb(): LiveData<List<Movies>> {
      return movieDao.getAll()
    }

    fun filterName() : LiveData<List<Movies>> = movieDao.filterByName()

    fun filterYear() : LiveData<List<Movies>> = movieDao.filterByYear()

}