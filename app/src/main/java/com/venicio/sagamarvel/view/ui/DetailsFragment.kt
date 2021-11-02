package com.venicio.sagamarvel.view.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.venicio.sagamarvel.R
import com.venicio.sagamarvel.data.db.model.FavoriteEntity
import com.venicio.sagamarvel.data.model.Movies
import com.venicio.sagamarvel.data.repository.MovieRepository
import com.venicio.sagamarvel.databinding.FragmentDetailsBinding
import com.venicio.sagamarvel.viewmodel.SagaMarvelDetailsViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val isSaved: Boolean = false
    private val args  by navArgs<DetailsFragmentArgs>()
    private val dViewModel: SagaMarvelDetailsViewModel by viewModel {
        parametersOf(MovieRepository(get()), args)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        setHasOptionsMenu(true)
        showDetails()

        return (binding.root)
    }

    private fun showDetails() {
        dViewModel.detailsMovies.observe(viewLifecycleOwner,{ data ->

            data?.let {
                Glide.with(binding.ivPosterMovieDetail)
                    .load(args.dataMovies.poster)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivPosterMovieDetail)

                binding.tvInputTitleMovieDetail.text = args.dataMovies.title
                binding.tvInputYearMovieDetail.text = args.dataMovies.year
                binding.tvInputRatedMovieDetail.text = args.dataMovies.rated
                binding.tvInputReleasedMovieDetail.text = args.dataMovies.released
                binding.tvInputRuntimeMovieDetail.text = args.dataMovies.runtime
                binding.tvInputGenreMovieDetail.text = args.dataMovies.genre
                binding.tvInputDirectorMovieDetail.text = args.dataMovies.director
                binding.tvInputWriterMovieDetail.text = args.dataMovies.writer
                binding.tvInputActorsMovieDetail.text = args.dataMovies.actors
                binding.tvInputPlotMovieDetail.text = args.dataMovies.plot
            }
        })
    }

    private fun movie(): Movies {
        return args.dataMovies
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_favorite, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == R.id.ic_add_favorite && !isSaved) {
                saveFavorites(item)
                Toast.makeText(context, "SALVO NO FAVORITO", Toast.LENGTH_SHORT).show()
            } else if (item.itemId == R.id.ic_add_favorite && isSaved) {
                removeFavorites(item)
            }
        return super.onOptionsItemSelected(item)
    }

    private fun removeFavorites(item: MenuItem) {
        TODO("Not yet implemented")
    }

    private  fun saveFavorites(item: MenuItem) {

        val favoriteEntity = FavoriteEntity("", "","","","","","","","","","")

        dViewModel.insertFav(favoriteEntity)
    }
}