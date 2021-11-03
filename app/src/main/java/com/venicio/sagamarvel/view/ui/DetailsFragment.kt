package com.venicio.sagamarvel.view.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.venicio.sagamarvel.R
import com.venicio.sagamarvel.data.model.toFavoriteEntity
import com.venicio.sagamarvel.data.repository.MovieRepository
import com.venicio.sagamarvel.databinding.FragmentDetailsBinding
import com.venicio.sagamarvel.viewmodel.FavoritesViewModel
import com.venicio.sagamarvel.viewmodel.SagaMarvelDetailsViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private var isSaved: Boolean = false
    private var savedMovie = "title"
    private val args by navArgs<DetailsFragmentArgs>()
    private val dViewModel: SagaMarvelDetailsViewModel by viewModel {
        parametersOf(MovieRepository(get()), args)
    }
    private val fViewModel: FavoritesViewModel by viewModel {
        parametersOf(MovieRepository(get()))
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
        dViewModel.detailsMovies.observe(viewLifecycleOwner, { data ->
            data?.let {
                Glide.with(binding.ivPosterMovieDetail)
                    .load(args.dataMovies!!.poster)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivPosterMovieDetail)

                binding.tvInputTitleMovieDetail.text = args.dataMovies?.title
                binding.tvInputYearMovieDetail.text = args.dataMovies?.year
                binding.tvInputRatedMovieDetail.text = args.dataMovies?.rated
                binding.tvInputReleasedMovieDetail.text = args.dataMovies?.released
                binding.tvInputRuntimeMovieDetail.text = args.dataMovies?.runtime
                binding.tvInputGenreMovieDetail.text = args.dataMovies?.genre
                binding.tvInputDirectorMovieDetail.text = args.dataMovies?.director
                binding.tvInputWriterMovieDetail.text = args.dataMovies?.writer
                binding.tvInputActorsMovieDetail.text = args.dataMovies?.actors
                binding.tvInputPlotMovieDetail.text = args.dataMovies?.plot
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_add_favorite, menu)
        val itemMenu: MenuItem = menu.findItem(R.id.ic_add_favorite)
        checkSavedFavorites(itemMenu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ic_add_favorite && !isSaved) {
            saveFavorites(item)

           val layout = layoutInflater.inflate(R.layout.custom_toast_success,  binding.root, false)
            Toast(requireContext()).apply {
                duration = Toast.LENGTH_SHORT
                setGravity(Gravity.CENTER, 0,0)
                view = layout
            }.show()


        } else if (item.itemId == R.id.ic_add_favorite && isSaved) {
            args.dataMovies?.toFavoriteEntity()?.let { removeFavorites(item) }

            val layout = layoutInflater.inflate(R.layout.custom_toast_removed,  binding.root, false)
            Toast(requireContext()).apply {
                duration = Toast.LENGTH_SHORT
                setGravity(Gravity.CENTER, 0,0)
                view = layout
            }.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveFavorites(item: MenuItem) {
        val favEntity = args.dataMovies?.toFavoriteEntity()
        if (favEntity != null) {
            fViewModel.insertFav(favEntity)
            isSaved = true
            item.setIcon(R.drawable.ic_favorite_added)
        }
    }

    private fun checkSavedFavorites(item: MenuItem) {
        fViewModel.favoritesLiveData.observe(viewLifecycleOwner) {
            try {
                for (savedMovie in it) {
                    if (savedMovie.title == args.dataMovies!!.toFavoriteEntity().title  ) {
                        item.setIcon(R.drawable.ic_favorite_added)
                        this.savedMovie = savedMovie.title
                        isSaved = true
                    }
                }
            } catch (e: Exception) {
                Log.d("DetailsFragment", e.message.toString())
            }
        }
    }

    private fun removeFavorites(item: MenuItem) {
        val favEntityTitle = args.dataMovies!!.title
        fViewModel.removeFav(favEntityTitle)
        isSaved = false
        item.setIcon(R.drawable.ic_add_favorite_)
    }
}