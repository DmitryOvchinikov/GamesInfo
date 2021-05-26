package com.classy.gamesinfo.data.repository

import com.classy.gamesinfo.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getRecentArticles(format: String, sort: String, limit: Int, offset: Int) = apiHelper.getRecentArticles(format, sort, limit, offset)
    suspend fun getRecentReviews() = apiHelper.getRecentReviews()
    suspend fun getRecentVideos() = apiHelper.getRecentVideos()
}