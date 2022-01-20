package com.fourthwall.web.movie.customer.input

import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class MovieRatingInput(

    @get:Min(1)
    @get:Max(5)
    val ratingStar: Int
)
