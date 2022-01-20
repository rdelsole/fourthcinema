package com.fourthwall.usecase.movie

import java.time.LocalTime

interface UpdateMovieTime {

    fun execute(movieId: Long, movieRoomScheduleId: Long, time: LocalTime)
}
