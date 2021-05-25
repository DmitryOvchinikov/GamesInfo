package com.classy.gamesinfo.data.model.gamespot

data class Release(
    val api_detail_url: String,
    val distribution_type: String,
    val id: Int,
    val name: String,
    val platform: String,
    val region: String,
    val upc: String
)