package com.classy.gamesinfo.data.api

class ApiHelper(private val gameSpotService: GameSpotService) {
    suspend fun getRecentArticles() = gameSpotService.getRecentArticles()
    suspend fun getRecentReviews() = gameSpotService.getRecentReviews()
    suspend fun getRecentVideos() = gameSpotService.getRecentVideos()
}