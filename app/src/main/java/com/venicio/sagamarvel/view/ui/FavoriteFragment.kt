package com.venicio.sagamarvel.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import androidx.navigation.fragment.navArgs
import com.venicio.sagamarvel.data.repository.MovieRepository
import com.venicio.sagamarvel.databinding.FragmentFavoriteBinding
import com.venicio.sagamarvel.view.adapter.FavoritesAdapter
import com.venicio.sagamarvel.view.adapter.SagaMarvelAdapter
import com.venicio.sagamarvel.view.ui.DetailsFragmentArgs
import com.venicio.sagamarvel.viewmodel.FavoritesViewModel
import com.venicio.sagamarvel.viewmodel.SagaMarvelDetailsViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteRecyclerAdapter: FavoritesAdapter
    private val fViewModel: FavoritesViewModel by viewModel {
        parametersOf(MovieRepository(get()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        setupFavoriteRecycler()
        setupObserverFavorites()

        return (binding.root)
    }

    private fun setupObserverFavorites() {
        fViewModel.favoritesLiveData.observe(viewLifecycleOwner,{
            setupFavoriteRecycler()
            favoriteRecyclerAdapter.submitList(it)
        })
    }

    private fun setupFavoriteRecycler() {
        val recyclerFav = binding.rvFavoriteMovies
        recyclerFav.setHasFixedSize(true)
        favoriteRecyclerAdapter = FavoritesAdapter()
        recyclerFav.adapter = favoriteRecyclerAdapter
    }

}