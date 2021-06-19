package com.example.moviecatalogue.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.SectionFavoritePagerAdapter
import com.example.moviecatalogue.databinding.ActivityFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {

    private lateinit var activityFavoriteBinding : ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)
        setupViewPager()
        supportActionBar?.title = getString(R.string.favorite)

    }

    private fun setupViewPager(){
        activityFavoriteBinding.viewPager.adapter = SectionFavoritePagerAdapter(this)
        TabLayoutMediator(activityFavoriteBinding.tabs,activityFavoriteBinding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.movie)
                1 -> tab.text = resources.getString(R.string.tv_show)
            }
        }.attach()
    }
}