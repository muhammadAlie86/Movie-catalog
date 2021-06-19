package com.example.moviecatalogue.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.api.Constant.Companion.BACKDROP_IMAGE_URL
import com.example.moviecatalogue.api.Constant.Companion.POSTER_IMAGE_URL
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.model.remote.entity.Movie
import com.example.moviecatalogue.model.remote.entity.TvShow
import com.example.moviecatalogue.viewmodel.DetailViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {


    private lateinit var detailBinding: ActivityDetailBinding

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val MOVIE_TYPE_ID = "Movie"
        const val TV_TYPE_ID = "TV SHOW"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setProgress()

        val viewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        val extras = intent.extras
        if (extras != null) {
            setProgress()
            val movieId = extras.getInt(EXTRA_DATA, 0)
            val id = extras.getString(EXTRA_TYPE)
            when {
                id.equals(MOVIE_TYPE_ID) -> {
                    setTitle(R.string.movie)
                    hideProgress()
                    viewModel.setMovie(movieId).observe(this, populateMovie)
                }
                id.equals(TV_TYPE_ID) -> {
                    setTitle(R.string.tv_show)
                    hideProgress()
                    viewModel.setTvShow(movieId).observe(this, populateTv)

                }
                else -> {
                    detailBinding.imageEmpty.visibility = View.VISIBLE
                }
            }
        }


    }

    private val populateMovie = Observer<Movie> {

        detailBinding.tvTitle.text = StringBuilder("${it.title}")
        detailBinding.tvReleaseDate.text = StringBuilder("${it.release_date}")
        detailBinding.tvRate.text = StringBuilder("${it.vote_average}")
        detailBinding.tvLanguage.text = StringBuilder("${it.language}")
        detailBinding.tvDescription.text = StringBuilder("Overview :\n${it.overview}")
        Glide.with(this)
            .load(POSTER_IMAGE_URL + it.img_path)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_image_broken)
            )
            .into(detailBinding.imgPoster)
        Glide.with(this)
            .load(BACKDROP_IMAGE_URL + it.backdrop_img)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_image_broken)
            )
            .into(detailBinding.imgBackdrop)
    }
    private val populateTv = Observer<TvShow> {
        detailBinding.tvTitle.text = StringBuilder("${it.title}")
        detailBinding.tvReleaseDate.text = StringBuilder("${it.release_date}")
        detailBinding.tvRate.text = StringBuilder("${it.vote_average}")
        detailBinding.tvLanguage.text = StringBuilder("${it.language}")
        detailBinding.tvDescription.text = StringBuilder("Overview :\n${it.overview}")
        Glide.with(this)
            .load(POSTER_IMAGE_URL + it.imgPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_image_broken)
            )
            .into(detailBinding.imgPoster)
        Glide.with(this)
            .load(BACKDROP_IMAGE_URL + it.backdrop_img)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_image_broken)
            )
            .into(detailBinding.imgBackdrop)

    }

    private fun setProgress() {
        detailBinding.progressBar.visibility = View.VISIBLE
        detailBinding.tvTitle.visibility = View.GONE
        detailBinding.tvReleaseDate.visibility = View.GONE
        detailBinding.tvRate.visibility = View.GONE
        detailBinding.tvLanguage.visibility = View.GONE
        detailBinding.tvDescription.visibility = View.GONE
    }

    private fun hideProgress() {
        detailBinding.progressBar.visibility = View.GONE
        detailBinding.tvTitle.visibility = View.VISIBLE
        detailBinding.tvReleaseDate.visibility = View.VISIBLE
        detailBinding.tvRate.visibility = View.VISIBLE
        detailBinding.tvLanguage.visibility = View.VISIBLE
        detailBinding.tvDescription.visibility = View.VISIBLE
    }


}