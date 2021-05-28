package com.classy.gamesinfo.data.model.gamespot

data class ResultReview(
    val authors: String,
    val bad: String,
    val body: String,
    val deck: String,
    val game: Game,
    val good: String,
    val id: Int,
    val image: Image,
    val lede: String,
    val publish_date: String,
    val releases: List<Release>,
    val review_type: String,
    val score: String,
    val site_detail_url: String,
    val title: String,
    val update_date: String,
    var expanded: Boolean = false
)