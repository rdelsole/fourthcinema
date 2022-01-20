package com.fourthwall.usecase.movie

import java.math.BigDecimal

interface UpdateMoviePrice {

    fun execute(movieId: Long, movieRoomScheduleId: Long, price: BigDecimal)
}
