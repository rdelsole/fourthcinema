package com.fourthwall.web.movie.admin.input

import javax.validation.constraints.Pattern

data class UpdateTimeInput(
    @get:Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]")
    val time: String
)
