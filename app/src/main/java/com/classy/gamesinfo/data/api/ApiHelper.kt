package com.classy.gamesinfo.data.api

class ApiHelper(private val igdbService: IgdbService) {
    suspend fun getAllGames(accessToken: String) = igdbService.getAllGames(accessToken)
    suspend fun authenticate() = igdbService.authenticate()
}