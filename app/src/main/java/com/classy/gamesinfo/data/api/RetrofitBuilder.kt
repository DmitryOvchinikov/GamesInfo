package com.classy.gamesinfo.data.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://api.igdb.com/v4/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //Maybe not needed
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: IgdbAPI = getRetrofit().create(IgdbAPI::class.java)
}