package com.fourthwall.usecase.impl

import com.fourthwall.entity.Movie
import com.fourthwall.entity.MoviePriceRoomTime
import com.fourthwall.entity.MovieRating
import com.fourthwall.entity.OmdbDetail
import com.fourthwall.entity.Room
import com.fourthwall.exception.OmdbServerException
import com.fourthwall.repository.MovieOmdbDetailsRepository
import com.fourthwall.repository.MovieRatingRepository
import com.fourthwall.repository.MovieRoomScheduleRepository
import com.fourthwall.usecase.movie.impl.GetMovieDetailsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalTime

class GetMovieDetailsUseCaseTest {

    private val movieRoomScheduleRepository: MovieRoomScheduleRepository = mockk()
    private val movieRatingRepository: MovieRatingRepository = mockk()
    private val movieOmdbDetailsRepository: MovieOmdbDetailsRepository = mockk()

    private val getMovieDetailsUseCase = GetMovieDetailsUseCase(
        movieRoomScheduleRepository, movieRatingRepository, movieOmdbDetailsRepository
    )

    @Test
    fun `when movie is found should return all fully object`() {
        val movieId = 85L
        val omdbId = "omdbId"
        every { movieRoomScheduleRepository.findMovieTimesByMovieId(movieId) } returns buildMovieAndPriceRoomTime(movieId, omdbId)
        every { movieRatingRepository.findMovieRatingByMovieId(movieId) } returns buildMovieRating(movieId)
        every { movieOmdbDetailsRepository.getMovieOmdbDetails(omdbId) } returns buildOmdbDetail()

        val result = getMovieDetailsUseCase.execute(movieId)

        Assertions.assertTrue(result != null)
        Assertions.assertTrue(result!!.id == movieId)
        Assertions.assertTrue(result.moviePriceRoom!!.size == 1)
        Assertions.assertTrue(result.moviePriceRoom!!.first().price == BigDecimal.TEN)

        Assertions.assertFalse(result.fourthCinemaRating.isNullOrEmpty())
        Assertions.assertTrue(result.fourthCinemaRating!!.keys.size == 5)
        Assertions.assertTrue(result.fourthCinemaRating!![1] == 1L)
        Assertions.assertTrue(result.fourthCinemaRating!![2] == 2L)
        Assertions.assertTrue(result.fourthCinemaRating!![3] == 3L)
        Assertions.assertTrue(result.fourthCinemaRating!![4] == 0L)
        Assertions.assertTrue(result.fourthCinemaRating!![5] == 0L)
        Assertions.assertTrue(result.omdbDetail!!.year == "2021")
    }

    @Test
    fun `when movie is null should return null and not call other repositories`() {
        val movieId = 86L

        every { movieRoomScheduleRepository.findMovieTimesByMovieId(movieId) } returns null

        val result = getMovieDetailsUseCase.execute(movieId)

        Assertions.assertTrue(result == null)

        verify(exactly = 0) { movieRatingRepository.findMovieRatingByMovieId(any()) }
        verify(exactly = 0) { movieOmdbDetailsRepository.getMovieOmdbDetails(any()) }
    }

    @Test
    fun `when no rating is found should return the movie rating filled by 0`() {
        val movieId = 88L
        val omdbId = "omdbId"
        every { movieRoomScheduleRepository.findMovieTimesByMovieId(movieId) } returns buildMovieAndPriceRoomTime(movieId, omdbId)
        every { movieRatingRepository.findMovieRatingByMovieId(movieId) } returns emptyList()
        every { movieOmdbDetailsRepository.getMovieOmdbDetails(omdbId) } returns buildOmdbDetail()

        val result = getMovieDetailsUseCase.execute(movieId)

        Assertions.assertTrue(result != null)
        Assertions.assertTrue(result!!.id == movieId)
        Assertions.assertTrue(result.moviePriceRoom!!.size == 1)
        Assertions.assertTrue(result.moviePriceRoom!!.first().price == BigDecimal.TEN)

        Assertions.assertTrue(result.fourthCinemaRating!!.keys.size == 5)
        Assertions.assertTrue(result.fourthCinemaRating!![1] == 0L)
        Assertions.assertTrue(result.fourthCinemaRating!![2] == 0L)
        Assertions.assertTrue(result.fourthCinemaRating!![3] == 0L)
        Assertions.assertTrue(result.fourthCinemaRating!![4] == 0L)
        Assertions.assertTrue(result.fourthCinemaRating!![5] == 0L)
        Assertions.assertTrue(result.omdbDetail!!.year == "2021")
    }

    @Test
    fun `when omdb throw an exception should throw this exception for nest layer`() {
        val movieId = 88L
        val omdbId = "omdbId"
        every { movieRoomScheduleRepository.findMovieTimesByMovieId(movieId) } returns buildMovieAndPriceRoomTime(movieId, omdbId)
        every { movieRatingRepository.findMovieRatingByMovieId(movieId) } returns emptyList()
        every { movieOmdbDetailsRepository.getMovieOmdbDetails(omdbId) } throws OmdbServerException("Simple")

        Assertions.assertThrows(OmdbServerException::class.java) { getMovieDetailsUseCase.execute(movieId) }
    }

    private fun buildMovieAndPriceRoomTime(movieId: Long, omdbId: String): Movie {
        return Movie(movieId, "Unit Test", omdbId, listOf(createMoviePriceRoomTime()), null, null)
    }

    private fun createMoviePriceRoomTime() = MoviePriceRoomTime(
        1L, BigDecimal.TEN, LocalTime.of(12, 0, 0), Room(1, 20)
    )

    private fun buildMovieRating(movieId: Long) = listOf(
        MovieRating(movieId, 1), MovieRating(movieId, 2), MovieRating(movieId, 2),
        MovieRating(movieId, 3), MovieRating(movieId, 3), MovieRating(movieId, 3)
    )

    private fun buildOmdbDetail() = OmdbDetail("2021", "action", "director", "writer", "actors", "plot", "rating")
}
