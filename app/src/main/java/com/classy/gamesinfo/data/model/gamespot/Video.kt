package com.classy.gamesinfo.data.model.gamespot

import com.classy.gamesinfo.data.model.gamespot.Association
import com.classy.gamesinfo.data.model.gamespot.Category
import com.classy.gamesinfo.data.model.gamespot.Image

data class Video(
    val associations: List<Association>,
    val categories: List<Category>,
    val deck: String,
    val hd_url: String,
    val high_url: String,
    val id: Int,
    val image: Image,
    val length_seconds: Int,
    val low_url: String,
    val mpx_id: String,
    val publish_date: String,
    val show: List<Any>,
    val site_detail_url: String,
    val source: String,
    val title: String
)