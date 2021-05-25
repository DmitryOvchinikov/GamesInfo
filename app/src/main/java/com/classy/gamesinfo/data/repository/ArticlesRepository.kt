package com.classy.gamesinfo.data.repository

import com.classy.gamesinfo.data.api.ApiHelper

class ArticlesRepository(private val apiHelper: ApiHelper) {
    suspend fun getRecentArticles() = apiHelper.getRecentArticles()
}