package com.fourthwall.usecase

import com.fourthwall.entity.Movie

interface ListMovieTimes {
    fun execute(movieId: Long): Movie?
}
