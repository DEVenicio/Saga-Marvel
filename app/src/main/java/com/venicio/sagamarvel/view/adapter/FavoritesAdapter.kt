package com.venicio.sagamarvel.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.venicio.sagamarvel.R
import com.venicio.sagamarvel.data.db.model.FavoriteEntity
import com.venicio.sagamarvel.data.db.model.toMovies
import com.venicio.sagamarvel.databinding.ItemMovieBinding
import com.venicio.sagamarvel.view.ui.FavoriteFragmentDirections
import com.venicio.sagamarvel.view.ui.HomeFragmentDirections

class FavoritesAdapter :
    ListAdapter<FavoriteEntity, FavoritesAdapter.FavoritesViewHolder>
        (FavoritesDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)

        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bindMovieFavorites(getItem(position))
    }


    class FavoritesViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindMovieFavorites(data: FavoriteEntity) {
            binding.tvTitleMovie.text = data.title
            binding.tvDateMovie.text = data.released
            binding.tvGenderMovie.text = data.genre

            Glide.with(binding.ivPosterMovie)
                .load(data.poster)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivPosterMovie)

            binding.cvContainerIvMovie.setOnClickListener {
                val directions =
               FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(data.toMovies())
                binding.root.findNavController().navigate(directions)
            }
        }
    }

    class FavoritesDiffUtil : DiffUtil.ItemCallback<FavoriteEntity>() {
        override fun areItemsTheSame(
            oldItem: FavoriteEntity,
            newItem: FavoriteEntity
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: FavoriteEntity,
            newItem: FavoriteEntity
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
}

