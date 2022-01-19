package com.fourthwall.omdb.response

import com.fasterxml.jackson.annotation.JsonProperty

data class OmdbRatingResponse(
    @JsonProperty(value = "Source")
    val source: String,

    @JsonProperty(value = "Value")
    val value: String
)
