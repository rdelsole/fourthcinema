package com.fourthwall.usecase.movie

interface SaveMovieRating {
    fun execute(movieId: Long, ratingStar: Int)
}
