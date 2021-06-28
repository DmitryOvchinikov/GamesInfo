package com.classy.gamesinfo.data.api

import com.classy.gamesinfo.BuildConfig
import com.classy.gamesinfo.data.model.gamespot.ArticleJsonAPI
import com.classy.gamesinfo.data.model.gamespot.GamesJsonAPI
import com.classy.gamesinfo.data.model.gamespot.ReviewJsonAPI
import com.classy.gamesinfo.data.model.gamespot.VideoJsonAPI
import retrofit2.http.*

interface GameSpotService {

    //Get all the recent articles/news from the API
    @GET("articles/?api_key=${BuildConfig.API_KEY}")
    suspend fun getRecentArticles(
        @Query("format") format: String,
        @Query("sort") sort: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ArticleJsonAPI

    //Get all the recent reviews from the API
    @GET("reviews/?api_key=${BuildConfig.API_KEY}")
    suspend fun getRecentReviews(
        @Query("format") format: String,
        @Query("sort") sort: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ReviewJsonAPI

    //Get all the recent videos from the API
    @GET("videos/?api_key=${BuildConfig.API_KEY}")
    suspend fun getRecentVideos(
        @Query("format") format: String,
        @Query("sort") sort: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): VideoJsonAPI

    //Get games from the API
    @GET("games/?api_key=${BuildConfig.API_KEY}")
    suspend fun getGames(
        @Query("format") format: String,
        @Query("sort") sort: String,
        @Query("limit") limit : Int,
        @Query("offset") offset: Int,
        @Query("filter") filter: String
    ): GamesJsonAPI
}