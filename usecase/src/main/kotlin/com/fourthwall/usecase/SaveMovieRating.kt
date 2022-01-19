package com.fourthwall.usecase

interface SaveMovieRating {
    fun execute(movieId: Long, ratingStar: Int)
}
