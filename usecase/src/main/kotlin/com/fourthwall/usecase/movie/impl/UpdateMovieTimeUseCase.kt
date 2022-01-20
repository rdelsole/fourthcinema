package com.fourthwall.usecase.movie.impl

import com.fourthwall.exception.MovieTimePriceNotUpdatedException
import com.fourthwall.repository.MovieRoomScheduleRepository
import com.fourthwall.usecase.movie.UpdateMovieTime
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalTime

@Service
class UpdateMovieTimeUseCase(
    private val movieRoomScheduleRepository: MovieRoomScheduleRepository
) : UpdateMovieTime {

    @Transactional
    override fun execute(movieId: Long, movieRoomScheduleId: Long, time: LocalTime) {
        movieRoomScheduleRepository.updateMovieTime(movieId, movieRoomScheduleId, time).run {
            if (this <= 0) throw MovieTimePriceNotUpdatedException(movieId, movieRoomScheduleId)
        }
    }
}
