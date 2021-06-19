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
import com.example.moviecatalogue.adapter.TvFavoriteAdapter
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.databinding.FragmentTvFavoriteBinding
import com.example.moviecatalogue.utils.DataCallbackTv
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.viewmodel.TvFavoriteViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class TvFavoriteFragment : Fragment(),DataCallbackTv {

    private lateinit var binding : FragmentTvFavoriteBinding
    private lateinit var tvFavoriteAdapter: TvFavoriteAdapter
    private lateinit var viewModel: TvFavoriteViewModel
    private lateinit var viewModelFactory: ViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obtainViewModel()
        showProgress()
        itemTouchHelper.attachToRecyclerView(binding.rvTvFavorite)

        if (activity != null) {
            tvFavoriteAdapter = TvFavoriteAdapter(this)

            viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, { tvFavorite ->
                if (tvFavorite != null) {
                    hideProgress()
                    tvFavoriteAdapter.apply {
                        submitList(tvFavorite)
                        notifyDataSetChanged()
                    }
                }

            })

            with(binding.rvTvFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvFavoriteAdapter
            }
        }
    }
    private fun obtainViewModel(){
        viewModelFactory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(TvFavoriteViewModel::class.java)
    }
    private fun showProgress(){
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgress(){
        binding.progressBar.visibility = View.GONE
        binding.rvTvFavorite.visibility = View.VISIBLE

    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.bindingAdapterPosition
                val tvShow = tvFavoriteAdapter.getSwipeData(swipedPosition)
                tvShow?.let { viewModel.setFavoriteTvShow(it) }

                val snackBar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.message_ok) {
                    tvShow?.let { viewModel.setFavoriteTvShow(it) }
                }
                snackBar.show()

            }
        }
    })

    override fun setDataTv(data: TvShow) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA,data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TV_TYPE_ID)
        startActivity(intent)

    }


}
