package com.example.moviecatalogue.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.adapter.TvShowAdapter
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.model.CatalogueEntity
import com.example.moviecatalogue.utils.DataCallback
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.viewmodel.TvShowViewModel

class TvShowFragment : Fragment(),DataCallback {

    private lateinit var fragmentTvShowFragment: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowFragment = FragmentTvShowBinding.inflate(layoutInflater,container,false)
        return fragmentTvShowFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                TvShowViewModel::class.java)
            val tvShow = viewModel.getTvShow()
            val tvShowAdapter = TvShowAdapter(this)
            tvShowAdapter.setTvShow(tvShow)

            with(fragmentTvShowFragment.rvTvShow) {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter = tvShowAdapter

            }
        }
    }

    override fun setData(data: CatalogueEntity) {

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA,data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE,DetailActivity.TV_TYPE_ID)
        startActivity(intent)
    }

}