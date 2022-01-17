package com.fourthwall.web.customer

import com.fourthwall.usecase.ListMovieTimes
import com.fourthwall.web.customer.output.MovieOutput
import com.fourthwall.web.customer.output.MoviePriceTimeRoomOutput.Companion.fromMoviePriceRoomTime
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/movie")
class MovieCustomerController(
    private val listMovieTimes: ListMovieTimes
) {

    @Operation(summary = "Endpoint to fetch movie times")
    @GetMapping("{movieId}/times")
    fun listMovieTimes(@PathVariable movieId: Long): ResponseEntity<MovieOutput> {
        return listMovieTimes.execute(movieId)?.let { movie ->
            val movieRoomDetail = movie.moviePriceRoom.map(::fromMoviePriceRoomTime)
            val movieOutput = MovieOutput(
                movie.id,
                movie.title,
                movieRoomDetail
            )
            ResponseEntity.ok(movieOutput)
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
