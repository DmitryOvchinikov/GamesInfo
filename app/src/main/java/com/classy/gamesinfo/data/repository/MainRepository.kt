package com.classy.gamesinfo.data.repository

import com.classy.gamesinfo.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getAllGames() = apiHelper.getAllGames()
    suspend fun authenticate() = apiHelper.authenticate()
}