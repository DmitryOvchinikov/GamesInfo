package com.classy.gamesinfo.data.model.gamespot

data class ArticleJsonAPI(
    val error: String,
    val limit: Int,
    val number_of_page_results: Int,
    val number_of_total_results: Int,
    val offset: Int,
    val results: List<ResultArticle>,
    val status_code: Int,
    val version: String
)