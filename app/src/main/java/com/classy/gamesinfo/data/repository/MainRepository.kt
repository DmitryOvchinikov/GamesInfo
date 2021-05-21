package com.classy.gamesinfo.data.repository

import com.classy.gamesinfo.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    fun getAllGames() = apiHelper.getAllGames()
    fun authenticate() = apiHelper.authenticate()
}