package com.example.xlampax

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.xlampax.viewmodels.ActivityViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.example.xlampax.adapters.ViewPagerAdapter
import com.example.xlampax.models.News
import com.example.xlampax.network.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var activityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager2 = viewpager
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPager2.adapter = viewPagerAdapter



        val tabLayout = tab_layout
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> "News"
                1 -> "Sports"
                else -> "Top"
            }
        }.attach()

        activityViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        observeGetPosts()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.getNews()
    }


    private fun observeGetPosts() {
        activityViewModel.simpleLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> viewOneLoading()
                Status.SUCCESS -> viewOneSuccess(it.data)
                Status.ERROR -> viewOneError(it.error)
            }
        })
    }
    private fun viewOneLoading() {
    }

    private fun viewOneSuccess(data: List<News>?) {
        progressBar.visibility = if (progressBar.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }

    private fun viewOneError(error: Error?) {
        error?.let {
            Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
        }
    }
}


