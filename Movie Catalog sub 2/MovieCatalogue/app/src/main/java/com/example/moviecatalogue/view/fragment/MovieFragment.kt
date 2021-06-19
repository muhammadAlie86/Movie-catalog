package com.example.moviecatalogue.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.adapter.MovieAdapter
import com.example.moviecatalogue.api.Constant.Companion.LANGUAGE
import com.example.moviecatalogue.api.Constant.Companion.PAGE
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.model.remote.entity.Movie
import com.example.moviecatalogue.utils.DataCallbackMovie
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.viewmodel.MovieViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment(), DataCallbackMovie {


    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null) {
            val movieAdapter = MovieAdapter(this)
            val viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(
                MovieViewModel::class.java
            )

            viewModel.getMovie(BuildConfig.API_KEY, LANGUAGE, PAGE)
                .observe(viewLifecycleOwner, {
                    movieAdapter.setMovie(it)
                    with(fragmentMovieBinding.rvMovie) {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)
                        adapter = movieAdapter
                    }
                })
        }

    }

    override fun setDataMovie(data: Movie) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.MOVIE_TYPE_ID)
        startActivity(intent)

    }
}