package com.venicio.sagamarvel.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.venicio.sagamarvel.data.model.Movies
import com.venicio.sagamarvel.databinding.ItemMovieBinding

class SagaMarvelAdapter:
    ListAdapter<Movies, SagaMarvelAdapter.SagaMarvelViewHolder>
    (MarvelDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SagaMarvelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)

        return SagaMarvelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SagaMarvelViewHolder, position: Int) {
        holder.bindMovie(getItem(position))
    }

    class SagaMarvelViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindMovie(data: Movies) {
            binding.tvTitleMovie.text = data.title
            binding.tvDateMovie.text = data.released
            binding.tvGenderMovie.text = data.genre

            Glide.with(binding.ivPosterMovie)
                .load(data.poster)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivPosterMovie)
        }
    }


    class MarvelDiffUtil : DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.title == newItem.title
        }
    }

}
