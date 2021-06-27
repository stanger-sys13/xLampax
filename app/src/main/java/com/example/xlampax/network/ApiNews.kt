package com.example.xlampax.network

import com.example.xlampax.models.News
import retrofit2.http.GET

interface ApiNews {
    @GET(" ")
    suspend fun getNews(
    ): List<News>
}