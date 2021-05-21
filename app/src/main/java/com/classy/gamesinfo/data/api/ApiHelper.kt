package com.classy.gamesinfo.data.api

class ApiHelper(private val igdbAPI: IgdbAPI) {
    fun getAllGames() = igdbAPI.getAllGames("asd")
    fun authenticate() = igdbAPI.authenticate()
}