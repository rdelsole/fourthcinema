package com.fourthwall.entity

data class Movie(
    val id: Long,
    val title: String,
    val omdbId: String,
    val moviePriceRoom: List<MoviePriceRoomTime>? = null,
    val fourthCinemaRating: Map<Int, Long>? = null,
    val omdbDetail: OmdbDetail? = null
)
