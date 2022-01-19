package com.fourthwall.web.customer

import com.fourthwall.exception.MovieNotFoundException
import com.fourthwall.usecase.GetMovieDetails
import com.fourthwall.usecase.ListMovieTimes
import com.fourthwall.usecase.SaveMovieRating
import com.fourthwall.web.customer.input.MovieRatingInput
import com.fourthwall.web.customer.output.DetailOutput
import com.fourthwall.web.customer.output.MovieOutput
import com.fourthwall.web.customer.output.MoviePriceTimeRoomOutput.Companion.fromMoviePriceRoomTime
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/movie")
class MovieCustomerController(
    private val listMovieTimes: ListMovieTimes,
    private val saveMovieRating: SaveMovieRating,
    private val getMovieDetails: GetMovieDetails
) {

    @Operation(summary = "Fetch movie times")
    @ExceptionHandler(value = [MovieNotFoundException::class])
    @GetMapping("{movieId}/times")
    fun listMovieTimes(@PathVariable movieId: Long): ResponseEntity<MovieOutput> {
        return listMovieTimes.execute(movieId)?.let { movie ->
            val movieRoomDetail = movie.moviePriceRoom?.map(::fromMoviePriceRoomTime)
            val movieOutput = MovieOutput(
                movie.id,
                movie.title,
                movieRoomDetail
            )
            ResponseEntity.ok(movieOutput)
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @Operation(summary = "Endpoint to submit a rating for movies")
    @PostMapping("{movieId}/rating")
    @RequestBody(
        description = "Rating input", required = true,
        content = [Content(schema = Schema(implementation = MovieRatingInput::class))]
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun saveMovieRating(@PathVariable movieId: Long, @Valid @org.springframework.web.bind.annotation.RequestBody movieRatingInput: MovieRatingInput) {
        try {
            saveMovieRating.execute(movieId, movieRatingInput.ratingStar)
        } catch (e: MovieNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @Operation(summary = "Endpoint to fetch all details")
    @GetMapping("{movieId}")
    fun getMovieDetails(@PathVariable movieId: Long): ResponseEntity<MovieOutput> {
        val movie = getMovieDetails.execute(movieId)?.let { movie ->
            MovieOutput(
                id = movie.id,
                title = movie.title,
                moviePriceRoom = movie.moviePriceRoom?.map(::fromMoviePriceRoomTime),
                rating = movie.fourthCinemaRating,
                detail = movie.omdbDetail?.let { DetailOutput.fromOmdbDetail(it) }
            )
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(movie)
    }
}
