package com.fourthwall.repository

import com.fourthwall.entity.Movie

interface MovieRoomScheduleRepository {

    fun findMovieTimesByMovieId(movieId: Long): Movie?
}
