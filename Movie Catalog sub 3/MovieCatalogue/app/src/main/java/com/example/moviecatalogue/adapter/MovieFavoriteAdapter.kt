package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.api.Constant.Companion.POSTER_IMAGE_URL
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.databinding.ItemListCatalogueBinding
import com.example.moviecatalogue.utils.DataCallbackMovie

class MovieFavoriteAdapter(private val callbackMovie: DataCallbackMovie) : PagedListAdapter<Movie,MovieFavoriteAdapter.MyViewHolder>(
    DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id

            }
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }

    }
    fun getSwipeData(swipedPosition : Int) : Movie? = getItem(swipedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemListBinding = ItemListCatalogueBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieFavorite = getItem(position)
        if (movieFavorite != null) {
            holder.bind(movieFavorite)
        }
    }

    inner class MyViewHolder (private val binding : ItemListCatalogueBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {

            with(binding) {
                binding.tvTitle.text = movie.title
                binding.tvReleaseDate.text = movie.release_date
                binding.tvDescription.text = movie.overview

                imgPoster.setOnClickListener { callbackMovie.setDataMovie(movie) }

                Glide.with(itemView.context)
                    .load(POSTER_IMAGE_URL + movie.img_path)
                    .into(imgPoster)
            }
        }
    }
}