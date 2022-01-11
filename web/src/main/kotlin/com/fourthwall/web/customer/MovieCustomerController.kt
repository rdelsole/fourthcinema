package com.fourthwall.web.customer

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movie")
class MovieCustomerController {

    @Operation(summary = "Endpoint to fetch movie times")
    @GetMapping("{movieId}/times")
    fun listMovieTimes(): String {
        return "Hello World"
    }
}
