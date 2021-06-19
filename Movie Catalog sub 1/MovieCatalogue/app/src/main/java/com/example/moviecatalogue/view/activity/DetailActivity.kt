package com.example.moviecatalogue.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.databinding.ItemDetailCatalogueBinding
import com.example.moviecatalogue.model.CatalogueEntity
import com.example.moviecatalogue.viewmodel.DetailViewModel
import java.lang.StringBuilder

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ItemDetailCatalogueBinding

    companion object{
        const val EXTRA_DATA = "extra_data"
        const val MOVIE_TYPE_ID = "Movie"
        const val TV_TYPE_ID = "TV SHOW"
        const val EXTRA_TYPE = "extra_type"
        const val DATE = "Date : "
        const val DURATION = "Duration : "
        const val GENRE = "Genre : "
        const val OVERVIEW = "Overview : "
        const val DIRECTOR = "Director  "

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailBinding = activityDetailBinding.detailCatalogue
        setContentView(activityDetailBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setProgress()

        val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            DetailViewModel::class.java)
        val extras = intent.extras
        val typeId = extras?.getString(EXTRA_TYPE)
        if (extras != null) {
            val filmId = extras.getString(EXTRA_DATA)
            if (typeId.equals(MOVIE_TYPE_ID)) {
                if (filmId != null) {
                    setTitle(R.string.movie)
                    hideProgress()
                    viewModel.setMovie(filmId)
                    populateMovie(viewModel.getSelectedItem())
                }
            }
            else if (typeId.equals(TV_TYPE_ID)){
                if (filmId != null) {
                    setTitle(R.string.tv_show)
                    hideProgress()
                    viewModel.setTvShow(filmId)
                    populateTv(viewModel.getSelectedItemTv())
                }
            }
            else{
                detailBinding.imageEmpty.visibility = View.VISIBLE
            }
        }
    }
    private fun populateMovie(catalogueEntity: CatalogueEntity){
        detailBinding.tvTitleDetail.text =  catalogueEntity.title
        detailBinding.tvItemDate.text = StringBuilder( "$DATE ${catalogueEntity.date}")
        detailBinding.tvItemDuration.text = StringBuilder("$DURATION ${catalogueEntity.duration}")
        detailBinding.tvItemGenre.text = StringBuilder("$GENRE ${catalogueEntity.genre}")
        detailBinding.tvItemDescription.text = StringBuilder("$OVERVIEW \n${catalogueEntity.description}")
        detailBinding.tvItemDirector.text = StringBuilder("${catalogueEntity.director}\n$DIRECTOR")
        Glide.with(this)
            .load(catalogueEntity.imgPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_image_broken))
            .into(detailBinding.imgDetail)


    }
    private fun populateTv(catalogueEntity: CatalogueEntity){

        detailBinding.tvTitleDetail.text =  catalogueEntity.title
        detailBinding.tvItemDate.text = StringBuilder( "Date : ${catalogueEntity.date}")
        detailBinding.tvItemDuration.text = StringBuilder("Duration : ${catalogueEntity.duration}")
        detailBinding.tvItemGenre.text = StringBuilder("Genre : ${catalogueEntity.genre}")
        detailBinding.tvItemDescription.text = StringBuilder("Overview :\n${catalogueEntity.description}")
        detailBinding.tvItemDirector.text = StringBuilder("${catalogueEntity.director}\nDirector")
        Glide.with(this)
            .load(catalogueEntity.imgPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_image_broken))
            .into(detailBinding.imgDetail)


    }
    private fun setProgress(){
        detailBinding.progressBar.visibility = View.VISIBLE
        detailBinding.tvTitleDetail.visibility = View.GONE
        detailBinding.tvItemDate.visibility = View.GONE
        detailBinding.tvItemDuration.visibility = View.GONE
        detailBinding.tvItemGenre.visibility = View.GONE
        detailBinding.tvItemDescription.visibility = View.GONE
        detailBinding.tvItemDirector.visibility = View.GONE
    }
    private fun hideProgress(){
        detailBinding.progressBar.visibility = View.GONE
        detailBinding.tvTitleDetail.visibility = View.VISIBLE
        detailBinding.tvItemDate.visibility = View.VISIBLE
        detailBinding.tvItemDuration.visibility = View.VISIBLE
        detailBinding.tvItemGenre.visibility = View.VISIBLE
        detailBinding.tvItemDescription.visibility = View.VISIBLE
        detailBinding.tvItemDirector.visibility = View.VISIBLE
    }

}