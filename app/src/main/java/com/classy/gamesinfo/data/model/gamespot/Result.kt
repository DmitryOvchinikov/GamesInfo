package com.classy.gamesinfo.data.model.gamespot

data class Result(
    val associations: List<Association>,
    val authors: String,
    val body: String,
    val categories: List<Category>,
    val deck: String,
    val id: Int,
    val image: Image,
    val lede: String,
    val publish_date: String,
    val site_detail_url: String,
    val title: String,
    val update_date: String,
    var expanded: Boolean = false
)