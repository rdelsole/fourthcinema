package com.fourthwall.web.movie.customer.output

import com.fourthwall.entity.OmdbDetail

data class DetailOutput(
    val year: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val imdbRating: String
) {
    companion object {
        fun fromOmdbDetail(omdbDetail: OmdbDetail) = DetailOutput(
            omdbDetail.year,
            omdbDetail.genre,
            omdbDetail.director,
            omdbDetail.writer,
            omdbDetail.actors,
            omdbDetail.plot,
            omdbDetail.imdbRating
        )
    }
}
