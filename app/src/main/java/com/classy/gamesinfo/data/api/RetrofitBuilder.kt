package com.classy.gamesinfo.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://www.gamespot.com/api/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //val SERVICE_SERVICE: GameSpotService = getRetrofit().create(GameSpotService::class.java)
}