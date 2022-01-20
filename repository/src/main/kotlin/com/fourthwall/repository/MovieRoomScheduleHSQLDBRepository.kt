package com.fourthwall.repository

import com.fourthwall.entity.Movie
import com.fourthwall.entity.MoviePriceRoomTime
import com.fourthwall.entity.Room
import com.fourthwall.repository.jpa.MovieRoomScheduleJPARepository
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.sql.Time
import java.time.LocalTime

@Component
class MovieRoomScheduleHSQLDBRepository(
    private val movieRoomScheduleJPARepository: MovieRoomScheduleJPARepository
) : MovieRoomScheduleRepository {

    override fun findMovieTimesByMovieId(movieId: Long): Movie? {

        val movieRoomSchedulesModel = movieRoomScheduleJPARepository.findByMovieId(movieId)

        return movieRoomSchedulesModel.takeIf { it.isNotEmpty() }?.let { movieRoomScheduleList ->

            val movieRoomSchedules = movieRoomSchedulesModel.map {
                MoviePriceRoomTime(
                    it.id,
                    it.price,
                    it.hour.toLocalTime(),
                    Room(
                        it.room.number,
                        it.room.capacity
                    )
                )
            }
            Movie(
                movieRoomScheduleList.first().movie.id!!,
                movieRoomScheduleList.first().movie.title!!,
                movieRoomScheduleList.first().movie.imdbId!!,
                movieRoomSchedules,
                null, null
            )
        }
    }

    override fun updateMovieTime(movieId: Long, movieRoomScheduleId: Long, time: LocalTime): Int {
        return movieRoomScheduleJPARepository.updateMovieTime(movieId, movieRoomScheduleId, Time.valueOf(time))
    }

    override fun updateMoviePrice(movieId: Long, movieRoomScheduleId: Long, price: BigDecimal): Int {
        return movieRoomScheduleJPARepository.updateMoviePrice(movieId, movieRoomScheduleId, price)
    }
}
