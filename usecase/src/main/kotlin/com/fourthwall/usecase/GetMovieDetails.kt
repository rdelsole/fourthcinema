package com.fourthwall.usecase

import com.fourthwall.entity.Movie

interface GetMovieDetails {
    fun execute(movieId: Long): Movie?
}
