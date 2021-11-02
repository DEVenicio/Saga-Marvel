package com.venicio.sagamarvel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venicio.sagamarvel.data.model.Movies
import com.venicio.sagamarvel.data.repository.MovieRepository
import kotlinx.coroutines.launch
import java.io.IOException

class SagaMarvelViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.fetchAllMovies()
            }
            catch (networkError: IOException) {
                if (moviesLiveData.value.isNullOrEmpty())
                    networkError.message
            }
        }
    }

    val moviesLiveData = repository.movies

    val sortByName = repository.filterName
    val sortByYear = repository.filterYear

}