package com.fourthwall.web.movie.admin

import com.fourthwall.exception.MovieTimePriceNotUpdatedException
import com.fourthwall.usecase.movie.UpdateMoviePrice
import com.fourthwall.usecase.movie.UpdateMovieTime
import com.fourthwall.web.movie.admin.input.UpdatePriceInput
import com.fourthwall.web.movie.admin.input.UpdateTimeInput
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal
import java.time.LocalTime
import javax.validation.Valid

@RestController
@RequestMapping("/admin/movie")
class MovieAdminController(
    private val updateMovieTime: UpdateMovieTime,
    private val updateMoviePrice: UpdateMoviePrice
) {

    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Update time input, the value should be in HH:MM 24h format", required = true,
        content = [Content(schema = Schema(implementation = UpdateTimeInput::class))],

    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200"),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @PutMapping("{movieId}/time/{moviePriceTimeId}")
    fun updateMovieTime(
        @PathVariable("movieId") movieId: Long,
        @PathVariable("moviePriceTimeId") moviePriceTimeId: Long,
        @Valid @RequestBody updateTimeInput: UpdateTimeInput
    ) {
        try {
            val time = updateTimeInput.time.split(":").let { LocalTime.of(it[0].toInt(), it[1].toInt()) }
            updateMovieTime.execute(movieId, moviePriceTimeId, time)
        } catch (e: MovieTimePriceNotUpdatedException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Update price input, the value should be in 00.00 on max 99.99", required = true,
        content = [Content(schema = Schema(implementation = UpdatePriceInput::class))],

    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200"),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @PutMapping("{movieId}/price/{moviePriceTimeId}")
    fun updateMoviePrice(
        @PathVariable("movieId") movieId: Long,
        @PathVariable("moviePriceTimeId") moviePriceTimeId: Long,
        @Valid @RequestBody updatePriceInput: UpdatePriceInput
    ) {
        try {
            updateMoviePrice.execute(movieId, moviePriceTimeId, BigDecimal(updatePriceInput.price))
        } catch (e: MovieTimePriceNotUpdatedException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }
}
