package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.api.Constant.Companion.POSTER_IMAGE_URL
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.databinding.ItemListCatalogueBinding
import com.example.moviecatalogue.utils.DataCallbackMovie

class MovieAdapter (private val callbackMovie: DataCallbackMovie): RecyclerView.Adapter<MovieAdapter.MyViewHolder>(){

    private val listMovie = ArrayList<Movie>()

    fun setMovie(movies : List<Movie>){
        this.listMovie.addAll(movies)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemListCatalogueBinding = ItemListCatalogueBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemListCatalogueBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int = listMovie.size

    inner class MyViewHolder (private val binding: ItemListCatalogueBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie : Movie){
            with(binding){

                binding.tvTitle.text = movie.title
                binding.tvReleaseDate.text = movie.release_date
                binding.tvDescription.text = movie.overview

                imgPoster.setOnClickListener { callbackMovie.setDataMovie(movie) }

                Glide.with(itemView.context)
                    .load(POSTER_IMAGE_URL + movie.img_path)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_image_broken))
                    .into(imgPoster)
            }
        }
    }

}