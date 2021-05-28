package com.classy.gamesinfo.data.repository

import com.classy.gamesinfo.data.api.ApiHelper

class VideosRepository(private val apiHelper: ApiHelper) {
    suspend fun getRecentVideos(format: String, sort: String, limit: Int, offset: Int) = apiHelper.getRecentVideos(format, sort, limit, offset)
}