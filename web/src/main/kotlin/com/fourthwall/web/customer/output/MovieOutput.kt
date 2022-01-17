package com.fourthwall.web.customer.output

data class MovieOutput(
    val id: Long,
    val title: String,
    val moviePriceRoom: List<MoviePriceTimeRoomOutput>

)
