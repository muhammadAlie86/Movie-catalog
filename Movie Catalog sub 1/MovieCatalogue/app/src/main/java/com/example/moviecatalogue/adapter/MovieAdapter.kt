package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ItemListCatalogueBinding
import com.example.moviecatalogue.model.CatalogueEntity
import com.example.moviecatalogue.utils.DataCallback

class MovieAdapter (private val callback: DataCallback): RecyclerView.Adapter<MovieAdapter.MyViewHolder>(){

    private val listMovie = ArrayList<CatalogueEntity>()

    fun setMovie(movies : List<CatalogueEntity>){
        this.listMovie.clear()
        this.listMovie.addAll(movies)
        notifyDataSetChanged()
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

        fun bind(movie : CatalogueEntity){
            with(binding){
                tvItemTitle.text = movie.title

                imgPoster.setOnClickListener { callback.setData(movie) }

                Glide.with(itemView.context)
                    .load(movie.imgPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_image_broken))
                    .into(imgPoster)

            }
        }
    }

}