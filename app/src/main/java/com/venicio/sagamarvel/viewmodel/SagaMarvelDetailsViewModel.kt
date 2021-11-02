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

    val listFav: MutableLiveData<MutableList<Movies>> = MutableLiveData()
    private val _detailsMovies = MutableLiveData<Movies>()
    val detailsMovies: LiveData<Movies>
    get() = _detailsMovies

    init {
        getDataMovies()
        getFavorites()
        insertFav(fake())
    }

    private fun getDataMovies() {
        _detailsMovies.value = arguments.dataMovies
    }

    private fun getFavorites() {
        viewModelScope.launch {
            listFav.postValue(repository.findAllFavorites())
        }
    }

      fun insertFav(movieFav: FavoriteEntity) {
          viewModelScope.launch {
              repository.insertFav(movieFav)
          }
    }

    fun fake() : FavoriteEntity {

        return FavoriteEntity("a","b","c","d","e","f","g","h","y","j","k",)
    }

     suspend fun removeFav(movieFav: String) {
        repository.removeFav(movieFav)
    }

}