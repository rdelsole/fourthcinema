package com.fourthwall.repository

import com.fourthwall.entity.MovieRating
import com.fourthwall.exception.MovieNotFoundException
import com.fourthwall.repository.jpa.MovieRatingJPARepository
import com.fourthwall.repository.model.MovieEntity
import com.fourthwall.repository.model.MovieRatingEntity
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.stereotype.Component

@Component
class MovieRatingHSQLDBRepository(
    private val movieRatingJPARepository: MovieRatingJPARepository
) : MovieRatingRepository {

    override fun saveRating(movieId: Long, rating: Int) {
        try {
            val movie = MovieEntity(movieId, null, null)
            movieRatingJPARepository.save(MovieRatingEntity(null, movie, rating))
        } catch (e: InvalidDataAccessApiUsageException) {
            throw MovieNotFoundException(movieId)
        }
    }

    override fun findMovieRatingByMovieId(movieId: Long): List<MovieRating> {

        return movieRatingJPARepository.findByMovieId(movieId).map {
            MovieRating(movieId, it.ratingStar)
        }
    }
}

//
