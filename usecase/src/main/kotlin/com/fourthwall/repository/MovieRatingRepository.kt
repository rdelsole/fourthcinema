package com.fourthwall.repository

import com.fourthwall.entity.MovieRating

interface MovieRatingRepository {

    fun saveRating(movieId: Long, rating: Int)
    fun findMovieRatingByMovieId(movieId: Long): List<MovieRating>
}
