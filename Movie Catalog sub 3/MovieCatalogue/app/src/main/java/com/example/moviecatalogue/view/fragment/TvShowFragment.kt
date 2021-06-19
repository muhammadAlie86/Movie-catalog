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
import com.example.moviecatalogue.adapter.TvShowAdapter
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.utils.DataCallbackTv
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.viewmodel.TvViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class TvShowFragment : Fragment(),DataCallbackTv {

    private lateinit var binding: FragmentTvShowBinding
    private lateinit var viewModel: TvViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obtainViewModel()

        if (activity != null) {
            val tvAdapter = TvShowAdapter(this)
            viewModel.getTvShow().observe(viewLifecycleOwner, { tvShows ->
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> showProgress()
                        Status.SUCCESS -> {
                            hideProgress()
                            tvShows.data?.let { tvAdapter.setTvShow(it) }
                            tvAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> errorProgress()
                    }
                }
            })
            with(binding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }

        }
    }
    private fun obtainViewModel(){
        viewModelFactory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(TvViewModel::class.java)
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


    override fun setDataTv(data: TvShow) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA,data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TV_TYPE_ID)
        startActivity(intent)

    }
}