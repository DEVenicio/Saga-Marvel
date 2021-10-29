package com.venicio.sagamarvel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venicio.sagamarvel.data.model.Movies
import com.venicio.sagamarvel.data.model.MoviesResponse
import com.venicio.sagamarvel.data.repository.SagaMarvelRepository
import kotlinx.coroutines.launch

class SagaMarvelViewModel(
    private val rp: SagaMarvelRepository
) : ViewModel() {

        private val _allMovies = MutableLiveData<List<Movies>>()
    val allMovies: LiveData<List<Movies>>
        get() = _allMovies

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        viewModelScope.launch {
            _allMovies.postValue(rp.getAllMovies())
        }
    }

}