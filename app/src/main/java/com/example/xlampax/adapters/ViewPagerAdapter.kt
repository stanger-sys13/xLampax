package com.example.xlampax.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.xlampax.fragments.NewsFragment


open class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewsFragment.newInstance("top")
            1 -> NewsFragment.newInstance("news")
            2 -> NewsFragment.newInstance("sports")
            else -> NewsFragment.newInstance("top")
        }
    }
}