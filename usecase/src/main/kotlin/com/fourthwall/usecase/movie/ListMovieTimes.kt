package com.fourthwall.usecase.movie

import com.fourthwall.entity.Movie

interface ListMovieTimes {
    fun execute(movieId: Long): Movie?
}
