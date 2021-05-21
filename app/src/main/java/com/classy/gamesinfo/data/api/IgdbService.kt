package com.classy.gamesinfo.data.api

import com.classy.gamesinfo.data.model.Game
import com.classy.gamesinfo.data.model.IgdbAccess
import retrofit2.http.*

interface IgdbService {

    //        val client_id = "ihuqs2frs8uml6o6iwi75hl5q5a1iw"
    //        val client_secret = "5ructzjiii51d2noq5r1xxnslhtg3p"
    //        base url - https://api.igdb.com/v4

    //Provides an access token and the number of seconds until the token expires
    @POST("https://id.twitch.tv/oauth2/token?client_id=ihuqs2frs8uml6o6iwi75hl5q5a1iw&client_secret=5ructzjiii51d2noq5r1xxnslhtg3p&grant_type=client_credentials")
    suspend fun authenticate(): IgdbAccess


    //Static header with the known client-id, and a dynamic header of the once in a while changing authorization token
    @Headers("Client-ID:ihuqs2frs8uml6o6iwi75hl5q5a1iw")
    @POST("https://api.igdb.com/v4/games?fields=*;")
    suspend fun getAllGames(
        @Header("Authorization") token: String,
    ):
            ArrayList<Game>
}