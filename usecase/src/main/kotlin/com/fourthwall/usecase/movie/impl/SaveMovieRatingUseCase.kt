package com.fourthwall.usecase.movie.impl

import com.fourthwall.repository.MovieRatingRepository
import com.fourthwall.usecase.movie.SaveMovieRating
import org.springframework.stereotype.Service

@Service
class SaveMovieRatingUseCase(
    private val movieRatingRepository: MovieRatingRepository
) : SaveMovieRating {

    override fun execute(movieId: Long, ratingStar: Int) {
        movieRatingRepository.saveRating(movieId, ratingStar)
    }
}
