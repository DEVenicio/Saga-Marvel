package com.venicio.sagamarvel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.venicio.sagamarvel.data.repository.MovieRepository
import com.venicio.sagamarvel.databinding.FragmentFavoriteBinding
import com.venicio.sagamarvel.view.adapter.SagaMarvelAdapter
import com.venicio.sagamarvel.view.ui.DetailsFragmentArgs
import com.venicio.sagamarvel.viewmodel.SagaMarvelDetailsViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteRecyclerAdapter: SagaMarvelAdapter
    private val args by navArgs<DetailsFragmentArgs>()
    private val mViewModel: SagaMarvelDetailsViewModel by viewModel {
        parametersOf(MovieRepository(get()),args)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        setupFavoriteRecycler()
        setupObserve()

        return (binding.root)
    }

    private fun setupObserve() {
        mViewModel.listFav.observe(viewLifecycleOwner) {
            setupFavoriteRecycler()
            favoriteRecyclerAdapter.submitList(it)
        }
    }

    private fun setupFavoriteRecycler() {
        val recyclerFav = binding.rvFavoriteMovies
        recyclerFav.setHasFixedSize(true)
        favoriteRecyclerAdapter = SagaMarvelAdapter()
        recyclerFav.adapter = favoriteRecyclerAdapter
    }

}