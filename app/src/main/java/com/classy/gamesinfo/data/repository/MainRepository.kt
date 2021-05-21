package com.classy.gamesinfo.data.repository

import com.classy.gamesinfo.data.api.ApiHelper
import com.classy.gamesinfo.data.model.Game

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getAllGames(accessToken: String): ArrayList<Game> = apiHelper.getAllGames(accessToken)
    suspend fun authenticate() = apiHelper.authenticate()
}