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
import com.example.moviecatalogue.adapter.TvShowAdapter
import com.example.moviecatalogue.api.Constant.Companion.LANGUAGE
import com.example.moviecatalogue.api.Constant.Companion.PAGE
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.model.remote.entity.TvShow
import com.example.moviecatalogue.utils.DataCallbackTv
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.viewmodel.TvViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment(), DataCallbackTv {

    private lateinit var fragmentTvBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null) {
            val tvAdapter = TvShowAdapter(this)

            val viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(
                TvViewModel::class.java
            )
            viewModel.getTvShow(BuildConfig.API_KEY, LANGUAGE, PAGE)
                .observe(viewLifecycleOwner, {
                    tvAdapter.setTvShow(it)
                    with(fragmentTvBinding.rvTvShow) {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)
                        adapter = tvAdapter
                    }

                })
        }
    }

    override fun setDataTv(data: TvShow) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TV_TYPE_ID)
        startActivity(intent)

    }

}