package com.classy.gamesinfo.data.repository

import com.classy.gamesinfo.data.api.ApiHelper

class GamesRepository (private val apiHelper: ApiHelper) {
    suspend fun getGames(format: String, sort: String, limit: Int, offset: Int, filter: String) = apiHelper.getGames(format, sort, limit, offset, filter)
}