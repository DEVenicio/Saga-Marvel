package com.venicio.sagamarvel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.venicio.sagamarvel.data.db.model.FavoriteEntity
import com.venicio.sagamarvel.data.repository.MovieRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: MovieRepository
): ViewModel(){

    val favoritesLiveData = repository.favorites

      fun insertFav(movieFav: FavoriteEntity) {
          viewModelScope.launch {
              repository.insertFav(movieFav)
          }
    }

     fun removeFav(movieFav: String) {
         viewModelScope.launch {
             repository.removeFav(movieFav)
         }
    }


}
