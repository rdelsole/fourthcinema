package com.fourthwall.repository

import com.fourthwall.entity.Movie
import com.fourthwall.entity.MoviePriceRoomTime
import com.fourthwall.entity.Room
import com.fourthwall.repository.jpa.MovieRoomScheduleJPARepository
import org.springframework.stereotype.Component

@Component
class MovieRoomScheduleHSQLDBRepository(
    private val movieRoomScheduleJPARepository: MovieRoomScheduleJPARepository
) : MovieRoomScheduleRepository {

    override fun findMovieTimesByMovieId(movieId: Long): Movie? {

        val movieRoomSchedulesModel = movieRoomScheduleJPARepository.findByMovieId(movieId)

        return movieRoomSchedulesModel.takeIf { it.isNotEmpty() }?.let { movieRoomScheduleList ->

            val movieRoomSchedules = movieRoomSchedulesModel.map {
                MoviePriceRoomTime(
                    it.price,
                    it.hour.toLocalTime(),
                    Room(
                        it.room.number,
                        it.room.capacity
                    )
                )
            }
            Movie(movieRoomScheduleList.first().movie.id, movieRoomScheduleList.first().movie.title, movieRoomSchedules)
        }
    }
}
