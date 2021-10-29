package com.venicio.sagamarvel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.venicio.sagamarvel.data.model.Movies
import com.venicio.sagamarvel.view.ui.DetailsFragmentArgs

class SagaMarvelDetailsViewModel(
    private val arguments: DetailsFragmentArgs
) : ViewModel() {

    private val _detailsMovies = MutableLiveData<Movies>()
    val detailsMovies: LiveData<Movies>
    get() = _detailsMovies

    init {
        getDataMovies()
    }

    private fun getDataMovies() {
        _detailsMovies.value = arguments.dataMovies
    }
}