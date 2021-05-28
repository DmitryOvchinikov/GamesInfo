package com.classy.gamesinfo.data.api

class ApiHelper(private val gameSpotService: GameSpotService) {
    suspend fun getRecentArticles(format: String, sort: String, limit: Int, offset: Int) = gameSpotService.getRecentArticles(format, sort, limit, offset)
    suspend fun getRecentReviews(format: String, sort: String, limit: Int, offset: Int) = gameSpotService.getRecentReviews(format, sort, limit, offset)
    suspend fun getRecentVideos(format: String, sort: String, limit: Int, offset: Int) = gameSpotService.getRecentVideos(format, sort, limit, offset)
}