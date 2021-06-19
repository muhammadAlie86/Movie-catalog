package com.example.moviecatalogue.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.MovieAdapter
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.utils.DataCallbackMovie
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.viewmodel.MovieViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class MovieFragment : Fragment(), DataCallbackMovie {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obtainViewModel()
        if (activity != null) {
            val movieAdapter = MovieAdapter(this)

            viewModel.getMovie().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> showProgress()
                        Status.SUCCESS -> {
                            hideProgress()
                            movies.data?.let { movieAdapter.setMovie(it) }
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> errorProgress()
                    }
                }
            })
            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

    }
    private fun obtainViewModel(){
        viewModelFactory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(MovieViewModel::class.java)
    }
    private fun showProgress(){
        binding.progressBar.visibility = View.VISIBLE

    }
    private fun hideProgress(){
        binding.progressBar.visibility = View.GONE
    }
    private fun errorProgress(){
        binding.progressBar.visibility = View.GONE
        Toast.makeText(context, getString(R.string.failed_message), Toast.LENGTH_SHORT).show()
    }

    override fun setDataMovie(data: Movie) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA,data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE,DetailActivity.MOVIE_TYPE_ID)
        startActivity(intent)

    }
}