package com.fourthwall.usecase.movie

import com.fourthwall.entity.Movie

interface GetMovieDetails {
    fun execute(movieId: Long): Movie?
}
