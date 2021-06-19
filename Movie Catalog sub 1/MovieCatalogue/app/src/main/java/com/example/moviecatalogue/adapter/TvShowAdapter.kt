package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ItemListCatalogueBinding
import com.example.moviecatalogue.model.CatalogueEntity
import com.example.moviecatalogue.utils.DataCallback

class TvShowAdapter (private val callback: DataCallback): RecyclerView.Adapter<TvShowAdapter.MyViewHolder>(){

    private val listTvShow = ArrayList<CatalogueEntity>()

    fun setTvShow(tvShow : List<CatalogueEntity>){
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShow)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemListCatalogueBinding = ItemListCatalogueBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemListCatalogueBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = listTvShow[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int = listTvShow.size

    inner class MyViewHolder (private val binding: ItemListCatalogueBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow : CatalogueEntity){
            with(binding){
                tvItemTitle.text = tvShow.title

                imgPoster.setOnClickListener { callback.setData(tvShow) }

                Glide.with(itemView.context)
                    .load(tvShow.imgPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_image_broken))
                    .into(imgPoster)

            }
        }
    }

}