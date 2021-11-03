package com.venicio.sagamarvel.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.asLiveData
import com.venicio.sagamarvel.data.db.model.FavoriteEntity
import com.venicio.sagamarvel.data.model.Movies
import com.venicio.sagamarvel.data.network.SagaMarvelClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MovieRepository(
    private val dataSourceLocal: DataSourceLocal
) {

    private val service = SagaMarvelClient.getInstance()

    suspend fun fetchAllMovies() {
        withContext(Dispatchers.IO) {
            val moviesApi = service.fetchAllMoviesApi()
            for (m in moviesApi) {
                dataSourceLocal.insert(m)
            }
        }
    }

    val movies: LiveData<List<Movies>> = dataSourceLocal.fetchAllMoviesDb().asLiveData()

    val favorites: LiveData<List<FavoriteEntity>> = dataSourceLocal.findAllFavorites().asLiveData()

    val filterName: LiveData<List<Movies>> = dataSourceLocal.filterName()
    val filterYear: LiveData<List<Movies>> = dataSourceLocal.filterYear()

    fun searchFromDb(searchQuery: String) : LiveData<List<Movies>> {
        return dataSourceLocal.searchFromDatabase(searchQuery)
    }

    suspend fun insertFav(movieFav: FavoriteEntity ) {
        dataSourceLocal.insertFavorite(movieFav)
    }

    suspend fun removeFav(movieFav: String) {
        dataSourceLocal.removeFavorite(movieFav)
    }
}