package com.fourthwall.usecase.movie.impl

import com.fourthwall.entity.Movie
import com.fourthwall.entity.MovieRating
import com.fourthwall.repository.MovieOmdbDetailsRepository
import com.fourthwall.repository.MovieRatingRepository
import com.fourthwall.repository.MovieRoomScheduleRepository
import com.fourthwall.usecase.movie.GetMovieDetails
import org.springframework.stereotype.Service

@Service
class GetMovieDetailsUseCase(
    private val movieRoomScheduleRepository: MovieRoomScheduleRepository,
    private val movieRatingRepository: MovieRatingRepository,
    private val movieOmdbDetailsRepository: MovieOmdbDetailsRepository
) : GetMovieDetails {

    override fun execute(movieId: Long): Movie? {

        val movieTimes = movieRoomScheduleRepository.findMovieTimesByMovieId(movieId)

        return movieTimes?.copy(
            fourthCinemaRating = mountFourthCinemaRatings(movieId),
            omdbDetail = movieOmdbDetailsRepository.getMovieOmdbDetails(movieTimes.omdbId)
        )
    }

    private fun mountFourthCinemaRatings(movieId: Long) = movieRatingRepository.findMovieRatingByMovieId(movieId).fold(mutableMapOf(1 to 0L, 2 to 0L, 3 to 0L, 4 to 0L, 5 to 0L)) {
        acc: MutableMap<Int, Long>, movieRatingEntity: MovieRating ->
        acc.merge(movieRatingEntity.ratingStar, 1L, Long::plus)
        acc
    }
}
