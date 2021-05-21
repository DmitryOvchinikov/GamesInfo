package com.classy.gamesinfo.data.api

class ApiHelper(private val igdbAPI: IgdbAPI) {
    suspend fun getAllGames() = igdbAPI.getAllGames("asd")
    suspend fun authenticate() = igdbAPI.authenticate()
}