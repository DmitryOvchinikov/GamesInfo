package com.classy.gamesinfo.data.api

import com.classy.gamesinfo.data.model.Game
import com.classy.gamesinfo.data.model.IgdbAccess
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface IgdbAPI {

    //        val client_id = "ihuqs2frs8uml6o6iwi75hl5q5a1iw"
    //        val client_secret = "5ructzjiii51d2noq5r1xxnslhtg3p"

    //Provides an access token and the number of seconds until the token expires
    @POST("https://id.twitch.tv/oauth2/token?client_id=ihuqs2frs8uml6o6iwi75hl5q5a1iw&client_secret=5ructzjiii51d2noq5r1xxnslhtg3p&grant_type=client_credentials")
    fun authenticate(): Call<IgdbAccess>

    //base url - https://api.igdb.com/v4
    //have to include Client ID and access token in the HEADER of the request
    //EXAMPLE:

    //POST https://api.igdb.com/v4/games
    //Client-ID: abcdefg12345
    //Authorization: Bearer prau3ol6mg5glgek8m89ec2s9q5i3i   - Bearer is hard coded
    //fields *;


    //Static header with the known client-id, and a dynamic header of the constantly changing authorization token
    @Headers("Client-ID:ihuqs2frs8uml6o6iwi75hl5q5a1iw")
    @GET("games")
    fun getAllGames(@Header("Authorization") token: String): ArrayList<Game>
}