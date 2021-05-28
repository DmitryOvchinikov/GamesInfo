package com.classy.gamesinfo.data.repository

import com.classy.gamesinfo.data.api.ApiHelper

class ReviewsRepository(private val apiHelper: ApiHelper) {
    suspend fun getRecentReviews(format: String, sort: String, limit: Int, offset: Int) = apiHelper.getRecentReviews(format, sort, limit, offset)
}