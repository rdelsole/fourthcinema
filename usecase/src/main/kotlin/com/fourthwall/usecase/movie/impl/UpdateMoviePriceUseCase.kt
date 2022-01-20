package com.fourthwall.usecase.movie.impl

import com.fourthwall.exception.MovieTimePriceNotUpdatedException
import com.fourthwall.repository.MovieRoomScheduleRepository
import com.fourthwall.usecase.movie.UpdateMoviePrice
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class UpdateMoviePriceUseCase(
    private val movieRoomScheduleRepository: MovieRoomScheduleRepository
) : UpdateMoviePrice {

    @Transactional
    override fun execute(movieId: Long, movieRoomScheduleId: Long, price: BigDecimal) {
        movieRoomScheduleRepository.updateMoviePrice(movieId, movieRoomScheduleId, price).run {
            if (this <= 0) throw MovieTimePriceNotUpdatedException(movieId, movieRoomScheduleId)
        }
    }
}
