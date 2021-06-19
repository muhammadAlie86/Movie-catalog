package com.example.moviecatalogue.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.MovieFavoriteAdapter
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.databinding.FragmentMovieFavoriteBinding
import com.example.moviecatalogue.utils.DataCallbackMovie
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.viewmodel.MovieFavoriteViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MovieFavoriteFragment : Fragment(),DataCallbackMovie{

    private lateinit var binding: FragmentMovieFavoriteBinding
    private lateinit var movieFavoriteAdapter: MovieFavoriteAdapter
    private lateinit var viewModel: MovieFavoriteViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obtainViewModel()
        showProgress()
        itemTouchHelper.attachToRecyclerView(binding.rvMovieFavorite)

        if (activity != null){
            movieFavoriteAdapter = MovieFavoriteAdapter(this)
            viewModel.getFavoriteMovie().observe(viewLifecycleOwner,{ movieFavorite ->
                if (movieFavorite != null) {
                    hideProgress()
                    movieFavoriteAdapter.apply {
                        submitList(movieFavorite)
                        notifyDataSetChanged()
                    }
                }
            })

            with(binding.rvMovieFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieFavoriteAdapter
            }
        }
    }

    private fun obtainViewModel(){
        viewModelFactory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(MovieFavoriteViewModel::class.java)
    }
    private fun showProgress(){
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgress(){
        binding.progressBar.visibility = View.GONE
        binding.rvMovieFavorite.visibility = View.VISIBLE

    }
    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.bindingAdapterPosition
                val movie = movieFavoriteAdapter.getSwipeData(swipedPosition)
                movie?.let { viewModel.setFavoriteMovie(it) }
                val snackBar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.message_ok) {
                    movie?.let { viewModel.setFavoriteMovie(it) }
                }
                snackBar.show()
            }
        }
    })

    override fun setDataMovie(data: Movie) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA,data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.MOVIE_TYPE_ID)
        startActivity(intent)
    }

}