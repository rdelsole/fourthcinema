package com.fourthwall.web.movie.admin.input

import javax.validation.constraints.Pattern

data class UpdatePriceInput(
    @get:Pattern(regexp = "^(\\d{2})(\\.[0-9]{1,2})?")
    val price: String
)
