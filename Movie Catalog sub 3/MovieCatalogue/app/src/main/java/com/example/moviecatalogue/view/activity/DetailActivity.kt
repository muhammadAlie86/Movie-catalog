package com.example.moviecatalogue.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.api.Constant.Companion.BACKDROP_IMAGE_URL
import com.example.moviecatalogue.api.Constant.Companion.POSTER_IMAGE_URL
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.databinding.ItemDetailCatalogueBinding
import com.example.moviecatalogue.viewmodel.DetailViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ItemDetailCatalogueBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private var menu : Menu? = null

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val MOVIE_TYPE_ID = "Movie"
        const val TV_TYPE_ID = "TV SHOW"
        const val EXTRA_TYPE = "extra_type"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)
        obtainViewModel()
        detailBinding = activityDetailMovieBinding.detailCatalogue

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_DATA, 0)
            val id = extras.getString(EXTRA_TYPE)
            when {
                id.equals(MOVIE_TYPE_ID) -> {
                    setTitle(R.string.movie)
                    viewModel.setMovie(movieId)
                    viewModel.getMovieById(movieId).observe(this, { movie ->
                        if (movie != null) {
                            when (movie.status) {
                                Status.LOADING -> setProgress()
                                Status.SUCCESS -> {
                                    hideProgress()
                                    populateMovie(movie.data)
                                }
                                Status.ERROR -> errorProgress()
                            }
                        }
                    })
                }
                id.equals(TV_TYPE_ID) -> {
                    setTitle(R.string.tv_show)
                    viewModel.setTvShow(movieId)
                    viewModel.tvShowById(movieId).observe(this,{tvShow ->
                        if (tvShow != null) {
                            when (tvShow.status) {
                                Status.LOADING -> setProgress()
                                Status.SUCCESS -> {
                                    hideProgress()
                                    populateMovie(tvShow.data)
                                }
                                Status.ERROR -> errorProgress()
                            }
                        }
                    })
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)
        this.menu = menu
        val extras = intent.extras

        if (extras != null) {
            val id = extras.getString(EXTRA_TYPE)
            val isMovie = id == MOVIE_TYPE_ID

            if (isMovie) {

                viewModel.favoriteMovie.observe(this, { movieFavorite ->
                    if (movieFavorite != null) {
                        when (movieFavorite.status) {
                            Status.LOADING -> detailBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                detailBinding.progressBar.visibility = View.GONE
                                val state = movieFavorite.data?.isFavorite
                                state?.let { setFavoriteState(it) }

                            }
                            Status.ERROR -> errorProgress()
                        }
                    }
                })
            }
            else {
                viewModel.favoriteTv.observe(this, { tvFavorite ->
                    if (tvFavorite != null) {
                        when (tvFavorite.status) {
                            Status.LOADING ->
                                detailBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                detailBinding.progressBar.visibility = View.GONE
                                val state = tvFavorite.data?.isFavorite
                                state?.let { setFavoriteState(it) }
                            }
                            Status.ERROR -> errorProgress()
                        }
                    }
                })
            }
        }
        return super.onCreateOptionsMenu(menu)
    }
    private fun obtainViewModel(){
        viewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
    }
    private fun errorProgress(){
        detailBinding.progressBar.visibility = View.GONE
        Toast.makeText(this, getString(R.string.failed_message), Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.state_favorite) {
            val extras = intent.extras
            if (extras != null) {
                val id = extras.getString(EXTRA_TYPE)
                val isMovie : Boolean = id == MOVIE_TYPE_ID
                if (isMovie) {
                    viewModel.setFavoriteMovie()
                }
                else {
                    viewModel.setFavoriteTvShow()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state : Boolean) {
        if (menu == null ) return
        val menuItem = menu?.findItem(R.id.state_favorite)

        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this,R.drawable.ic_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this,R.drawable.ic_un_favorite)
        }
    }

    private fun populateMovie (movie: Movie?) {

        if (movie != null) {

            detailBinding.apply {
                tvTitle.text = StringBuilder("${movie.title}")
                tvReleaseDate.text = StringBuilder("${movie.release_date}")
                tvRate.text = StringBuilder("${movie.vote_average}")
                tvLanguage.text = StringBuilder("${movie.language}")
                tvDescription.text = StringBuilder("Overview :\n${movie.overview}")
            }

                Glide.with(this)
                        .load(POSTER_IMAGE_URL + movie.img_path)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_image_broken)
                        )
                        .into(detailBinding.imgPoster)

                Glide.with(this)
                        .load(BACKDROP_IMAGE_URL + movie.backdrop_img)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_image_broken)
                        )
                        .into(detailBinding.imgBackdrop)
            }
        }

    }
    private fun populateMovie (tvShow: TvShow?) {

        if (tvShow != null) {

            detailBinding.tvTitle.text = StringBuilder("${tvShow.title}")
            detailBinding.tvReleaseDate.text = StringBuilder("${tvShow.release_date}")
            detailBinding.tvRate.text = StringBuilder("${tvShow.vote_average}")
            detailBinding.tvLanguage.text = StringBuilder("${tvShow.language}")
            detailBinding.tvDescription.text = StringBuilder("Overview :\n${tvShow.overview}")


            Glide.with(this)
                .load(POSTER_IMAGE_URL + tvShow.imgPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_image_broken)
                )
                .into(detailBinding.imgPoster)

            Glide.with(this)
                .load(BACKDROP_IMAGE_URL + tvShow.backdrop_img)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_image_broken)
                )
                .into(detailBinding.imgBackdrop)
        }
    }
    private fun setProgress() {

        detailBinding.apply {
            progressBar.visibility = View.VISIBLE
            tvTitle.visibility = View.GONE
            tvReleaseDate.visibility = View.GONE
            tvRate.visibility = View.GONE
            tvLanguage.visibility = View.GONE
            tvDescription.visibility = View.GONE
        }

    }

    private fun hideProgress() {

        detailBinding.apply {
            progressBar.visibility = View.GONE
            tvTitle.visibility = View.VISIBLE
            tvReleaseDate.visibility = View.VISIBLE
            tvRate.visibility = View.VISIBLE
            tvLanguage.visibility = View.VISIBLE
            tvDescription.visibility = View.VISIBLE
        }

    }

}