package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.api.Constant.Companion.POSTER_IMAGE_URL
import com.example.moviecatalogue.databinding.ItemListCatalogueBinding
import com.example.moviecatalogue.model.remote.entity.TvShow
import com.example.moviecatalogue.utils.DataCallbackTv

class TvShowAdapter(private val callbackMovie: DataCallbackTv) :
    RecyclerView.Adapter<TvShowAdapter.MyViewHolder>() {

    private val listTvShow = ArrayList<TvShow>()


    fun setTvShow(tvShow: List<TvShow>) {
        this.listTvShow.addAll(tvShow)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemListCatalogueBinding =
            ItemListCatalogueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemListCatalogueBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = listTvShow[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int = listTvShow.size

    inner class MyViewHolder(private val binding: ItemListCatalogueBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            with(binding) {
                binding.tvTitle.text = tvShow.title
                binding.tvTitle.text = tvShow.title
                binding.tvReleaseDate.text = tvShow.release_date
                binding.tvDescription.text = tvShow.overview

                Glide.with(itemView.context)
                    .load(POSTER_IMAGE_URL + tvShow.imgPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_image_broken)
                    )
                    .into(imgPoster)

                imgPoster.setOnClickListener { callbackMovie.setDataTv(tvShow) }
            }
        }
    }

}