package com.classy.gamesinfo.data.model.gamespot

data class VideoJsonAPI(
    val error: String,
    val limit: Int,
    val number_of_page_results: Int,
    val number_of_total_results: Int,
    val offset: Int,
    val results: List<ResultVideo>,
    val status_code: Int,
    val version: String
)