package com.example.xlampax.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.xlampax.models.News
import com.example.xlampax.network.Event

class ActivityViewModel : NewsViewModel() {
    val simpleLiveData = MutableLiveData<Event<List<News>>>()

    fun getNews() {
        requestWithLiveData(simpleLiveData) {
            api.getNews()
        }
    }
}