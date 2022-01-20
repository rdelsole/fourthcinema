package com.fourthwall.repository

import com.fourthwall.entity.Movie
import java.math.BigDecimal
import java.time.LocalTime

interface MovieRoomScheduleRepository {

    fun findMovieTimesByMovieId(movieId: Long): Movie?
    fun updateMovieTime(movieId: Long, movieRoomScheduleId: Long, time: LocalTime): Int
    fun updateMoviePrice(movieId: Long, movieRoomScheduleId: Long, price: BigDecimal): Int
}
