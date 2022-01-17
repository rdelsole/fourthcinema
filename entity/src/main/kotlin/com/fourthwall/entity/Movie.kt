package com.fourthwall.entity

data class Movie(
    val id: Long,
    val title: String,
    val moviePriceRoom: List<MoviePriceRoomTime>
)
