package com.example.moviecatalogue.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.adapter.MovieAdapter
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.model.CatalogueEntity
import com.example.moviecatalogue.utils.DataCallback
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.viewmodel.MovieViewModel

class MovieFragment : Fragment(), DataCallback {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater,container,false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
                MovieViewModel::class.java)
            val movie = viewModel.getMovie()
            val movieAdapter = MovieAdapter(this)
            movieAdapter.setMovie(movie)

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter = movieAdapter

            }
        }
    }

    override fun setData(data: CatalogueEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA,data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE,DetailActivity.MOVIE_TYPE_ID)
        startActivity(intent)
    }
}