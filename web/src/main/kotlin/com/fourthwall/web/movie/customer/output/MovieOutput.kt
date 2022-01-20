package com.fourthwall.web.movie.customer.output

data class MovieOutput(
    val id: Long,
    val title: String,
    val moviePriceRoom: List<MoviePriceTimeRoomOutput>? = null,
    val rating: Map<Int, Long>? = null,
    val detail: DetailOutput? = null

)
