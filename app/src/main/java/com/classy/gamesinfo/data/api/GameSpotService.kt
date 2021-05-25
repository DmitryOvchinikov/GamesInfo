package com.classy.gamesinfo.data.api

import com.classy.gamesinfo.BuildConfig
import com.classy.gamesinfo.data.model.gamespot.Article
import com.classy.gamesinfo.data.model.gamespot.ArticlesJsonAPI
import com.classy.gamesinfo.data.model.gamespot.Review
import com.classy.gamesinfo.data.model.gamespot.Video
import retrofit2.http.*

interface GameSpotService {


    // API link to get "gaming" articles, news, etc
    // https://www.gamespot.com/api/articles/?api_key=b22f8ad64a1eb7f8155e386be1cd650aa08ed45f&sort=publish_date:desc

    //Provides an access token and the number of seconds until the token expires
//    @POST("https://id.twitch.tv/oauth2/token?client_id=ihuqs2frs8uml6o6iwi75hl5q5a1iw&client_secret=5ructzjiii51d2noq5r1xxnslhtg3p&grant_type=client_credentials")
//    suspend fun authenticate(): IgdbAccess
//
//
//    //Static header with the known client-id, and a dynamic header of the once in a while changing authorization token
//    @Headers("Client-ID:ihuqs2frs8uml6o6iwi75hl5q5a1iw")
//    @POST("https://api.igdb.com/v4/games?fields=name;&limit 100;")
//    suspend fun getAllGames(
//        @Header("Authorization") token: String,
//    ):
//            ArrayList<Game>

    //Get all the recent articles/news from the API
    @GET("articles/?api_key=${BuildConfig.API_KEY}&sort=publish_date:desc&format=json&limit=10")
    suspend fun getRecentArticles(): ArticlesJsonAPI
    //ArrayList<Article>

    //Get all the recent reviews from the API
    @GET("reviews/?api_key=${BuildConfig.API_KEY}&sort=publish_date:desc&format=json&limit=10")
    suspend fun getRecentReviews(): ArrayList<Review>

    //Get all the recent videos from the API
    @GET("videos/?api_key=${BuildConfig.API_KEY}&sort=publish_date:desc&format=json&limit=10")
    suspend fun getRecentVideos(): ArrayList<Video>

    //TODO: Implement quite a few GAMES API calls since the user can search by different values (genres, themes, franchises, release_dates etc)

}