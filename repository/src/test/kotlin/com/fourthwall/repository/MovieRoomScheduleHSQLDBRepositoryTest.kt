package com.fourthwall.repository

import com.fourthwall.repository.jpa.MovieJPARepository
import com.fourthwall.repository.jpa.MovieRoomScheduleJPARepository
import com.fourthwall.repository.jpa.RoomJPARepository
import com.fourthwall.repository.model.Movie
import com.fourthwall.repository.model.MovieRoomSchedule
import com.fourthwall.repository.model.Room
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.math.BigDecimal
import java.sql.Time
import java.time.LocalTime

@DataJpaTest
class MovieRoomScheduleHSQLDBRepositoryTest {

    @Autowired
    private lateinit var movieRoomScheduleJPARepository: MovieRoomScheduleJPARepository

    @Autowired
    private lateinit var movieJPARepository: MovieJPARepository

    @Autowired
    private lateinit var roomJPARepository: RoomJPARepository

    @Test
    fun `should return null when no movie is found`() {
        val movieRoomScheduleHSQLDBRepository = MovieRoomScheduleHSQLDBRepository(movieRoomScheduleJPARepository)
        Assertions.assertTrue(movieRoomScheduleHSQLDBRepository.findMovieTimesByMovieId(1) == null)
    }

    @Test
    fun `should return an Movie entity when movie is found`() {
        val movieRoomScheduleHSQLDBRepository = MovieRoomScheduleHSQLDBRepository(movieRoomScheduleJPARepository)

        val movieTitle = "Insertion"
        val movieId = 1L
        val roomNumber = 3
        val price = BigDecimal("12.85")

        val movieRoomScheduleId = 25L

        val movie = Movie(
            id = movieId,
            imdbId = "test",
            title = movieTitle
        )

        val room = Room(
            number = roomNumber,
            capacity = 85
        )

        val movieRoomSchedule = MovieRoomSchedule(
            id = movieRoomScheduleId,
            price = price,
            hour = Time.valueOf(LocalTime.of(14, 25, 18)),
            movie = movie,
            room = room
        )
        movieJPARepository.save(movie)
        roomJPARepository.save(room)
        movieRoomScheduleJPARepository.save(movieRoomSchedule)

        val movieFound = movieRoomScheduleHSQLDBRepository.findMovieTimesByMovieId(movieId)

        Assertions.assertTrue(movieFound != null)
        Assertions.assertTrue(movieFound!!.id == movieId)
        Assertions.assertTrue(movieFound.moviePriceRoom.size == 1)
        Assertions.assertTrue(movieFound.moviePriceRoom.first().price.equals(price))
        Assertions.assertTrue(movieFound.moviePriceRoom.first().roomDetails.number == roomNumber)
    }
}
