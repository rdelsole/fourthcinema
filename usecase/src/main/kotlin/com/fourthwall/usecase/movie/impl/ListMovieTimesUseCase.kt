package com.fourthwall.usecase.movie.impl

import com.fourthwall.entity.Movie
import com.fourthwall.repository.MovieRoomScheduleRepository
import com.fourthwall.usecase.movie.ListMovieTimes
import org.springframework.stereotype.Service

@Service
class ListMovieTimesUseCase(
    private val movieTimeRepository: MovieRoomScheduleRepository
) : ListMovieTimes {

    override fun execute(movieId: Long): Movie? {
        return movieTimeRepository.findMovieTimesByMovieId(movieId)
    }
}
