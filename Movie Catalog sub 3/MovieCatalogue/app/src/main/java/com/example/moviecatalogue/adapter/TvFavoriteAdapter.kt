package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.api.Constant.Companion.POSTER_IMAGE_URL
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.databinding.ItemListCatalogueBinding
import com.example.moviecatalogue.utils.DataCallbackTv

class TvFavoriteAdapter(private val callbackMovie: DataCallbackTv) : PagedListAdapter<TvShow,TvFavoriteAdapter.MyViewHolder> (DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id

            }
            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }
        }
    }
    fun getSwipeData(swipedPosition : Int) : TvShow? = getItem(swipedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemListBinding = ItemListCatalogueBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tvFavorite = getItem(position)
        if (tvFavorite != null) {
            holder.bind(tvFavorite)
        }
    }


    inner class MyViewHolder(private val binding : ItemListCatalogueBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {

            with(binding) {
                binding.tvTitle.text = tvShow.title
                binding.tvReleaseDate.text = tvShow.release_date
                binding.tvDescription.text = tvShow.overview


                Glide.with(itemView.context)
                    .load(POSTER_IMAGE_URL + tvShow.imgPath)
                    .into(imgPoster)

                imgPoster.setOnClickListener { callbackMovie.setDataTv(tvShow) }
            }
        }
    }
}