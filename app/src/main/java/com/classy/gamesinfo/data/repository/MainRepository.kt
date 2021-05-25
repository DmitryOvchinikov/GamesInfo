package com.classy.gamesinfo.data.repository

import com.classy.gamesinfo.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getRecentArticles() = apiHelper.getRecentArticles()
    suspend fun getRecentReviews() = apiHelper.getRecentReviews()
    suspend fun getRecentVideos() = apiHelper.getRecentVideos()
}