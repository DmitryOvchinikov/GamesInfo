package com.classy.gamesinfo.data.model.gamespot

data class GamesJsonAPI(
    val articles_api_url: String,
    val deck: String,
    val description: String,
    val franchises: List<Any>,
    val genres: List<Genre>,
    val id: Int,
    val image: Any,
    val images_api_url: String,
    val name: String,
    val release_date: String,
    val releases_api_url: String,
    val reviews_api_url: String,
    val site_detail_url: String,
    val themes: List<Any>,
    val videos_api_url: String
)