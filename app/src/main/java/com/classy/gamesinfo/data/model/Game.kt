package com.classy.gamesinfo.data.model

data class Game (
    val category: Int,
    val checksum: String,
    val cover: Int,
    val created_at: Int,
    val external_games: List<Int>,
    val first_release_date: Int,
    val game_modes: List<Int>,
    val genres: List<Int>,
    val id: Int,
    val keywords: List<Int>,
    val name: String,
    val platforms: List<Int>,
    val release_dates: List<Int>,
    val screenshots: List<Int>,
    val similar_games: List<Int>,
    val slug: String,
    val status: Int,
    val summary: String,
    val tags: List<Int>,
    val themes: List<Int>,
    val updated_at: Int,
    val url: String,
    val websites: List<Int>


) {
    override fun toString(): String {
        return "Game(category=$category, checksum='$checksum', cover=$cover, created_at=$created_at, external_games=$external_games, first_release_date=$first_release_date, game_modes=$game_modes, genres=$genres, id=$id, keywords=$keywords, name='$name', platforms=$platforms, release_dates=$release_dates, screenshots=$screenshots, similar_games=$similar_games, slug='$slug', status=$status, summary='$summary', tags=$tags, themes=$themes, updated_at=$updated_at, url='$url', websites=$websites)"
    }
}