package com.venicio.sagamarvel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venicio.sagamarvel.data.db.model.FavoriteEntity
import com.venicio.sagamarvel.data.model.Movies
import com.venicio.sagamarvel.data.repository.MovieRepository
import com.venicio.sagamarvel.view.ui.DetailsFragmentArgs
import kotlinx.coroutines.launch

class SagaMarvelDetailsViewModel(
    private val repository: MovieRepository,
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


//    val favoritesLiveData = repository.favorites
//
//      fun insertFav(movieFav: FavoriteEntity) {
//          viewModelScope.launch {
//              repository.insertFav(movieFav)
//          }
//    }
//
//     fun removeFav(movieFav: String) {
//         viewModelScope.launch {
//             repository.removeFav(movieFav)
//         }
//    }

}