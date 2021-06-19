package com.example.moviecatalogue.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviecatalogue.R
import com.example.moviecatalogue.view.fragment.MovieFragment
import com.example.moviecatalogue.view.fragment.TvShowFragment

class SectionPagerAdapter (private val mContext : Context,fm : FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object{

        @StringRes
        private val TAB_TITLE = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when (position){
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> Fragment()
        }


    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLE[position])
}