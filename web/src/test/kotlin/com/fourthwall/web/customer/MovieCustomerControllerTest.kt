package com.fourthwall.web.customer

import com.fourthwall.entity.Movie
import com.fourthwall.entity.MoviePriceRoomTime
import com.fourthwall.entity.Room
import com.fourthwall.usecase.ListMovieTimes
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.LocalTime

@WebMvcTest
class MovieCustomerControllerTest(
    @Autowired private val mockMvc: MockMvc
) {

    @MockkBean
    private lateinit var listMoviesTime: ListMovieTimes

    private val movieId = 55L
    private val movieTitle = "Simple movie"

    @Test
    fun `should return the movie with times`() {
        every { listMoviesTime.execute(movieId) } returns buildSimpleMovie()

        mockMvc.perform(get("/movie/$movieId/times").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.id").value(movieId))
            .andExpect(jsonPath("\$.title").value(movieTitle))
            .andExpect(jsonPath("\$.moviePriceRoom[0].price").value("32.99"))
            .andExpect(jsonPath("\$.moviePriceRoom[0].time").value("22:15"))
            .andExpect(jsonPath("\$.moviePriceRoom[0].room.number").value(2))
            .andExpect(jsonPath("\$.moviePriceRoom[0].room.capacity").value(25))
    }

    @Test
    fun `when movie returned is null should return 404`() {
        val notFoundMovieId = 12L
        every { listMoviesTime.execute(notFoundMovieId) } returns null

        mockMvc.perform(get("/movie/$notFoundMovieId/times").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
    }

    private fun buildSimpleMovie() = Movie(
        id = movieId,
        title = movieTitle,
        moviePriceRoom = listOf(
            MoviePriceRoomTime(
                price = BigDecimal("32.99"),
                time = LocalTime.of(22, 15, 35),
                roomDetails = Room(
                    number = 2,
                    capacity = 25
                )
            )
        )
    )
}
