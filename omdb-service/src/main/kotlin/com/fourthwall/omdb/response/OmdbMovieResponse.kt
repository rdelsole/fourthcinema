package com.fourthwall.omdb.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fourthwall.entity.OmdbDetail

data class OmdbMovieResponse(

    @JsonProperty(value = "Title")
    val title: String,

    @JsonProperty(value = "Year")
    val year: String,

    @JsonProperty(value = "Rated")
    val rated: String,

    @JsonProperty(value = "Released")
    val released: String,

    @JsonProperty(value = "Runtime")
    val runtime: String,

    @JsonProperty(value = "Genre")
    val genre: String,

    @JsonProperty(value = "Director")
    val director: String,

    @JsonProperty(value = "Writer")
    val writer: String,

    @JsonProperty(value = "Actors")
    val actors: String,

    @JsonProperty(value = "Plot")
    val plot: String,

    @JsonProperty(value = "Language")
    val language: String,

    @JsonProperty(value = "Country")
    val country: String,

    @JsonProperty(value = "Awards")
    val awards: String,

    @JsonProperty(value = "Poster")
    val poster: String,

    @JsonProperty(value = "Ratings")
    val ratings: List<OmdbRatingResponse>,

    @JsonProperty(value = "Metascore")
    val metascore: String,

    val imdbRating: String,
    val imdbVotes: String,
    val imdbID: String,

    @JsonProperty(value = "Type")
    val type: String,

    @JsonProperty(value = "DVD")
    val dvd: String,

    @JsonProperty(value = "BoxOffice")
    val boxOffice: String,

    @JsonProperty(value = "Production")
    val production: String,

    @JsonProperty(value = "Website")
    val website: String,

    @JsonProperty(value = "Response")
    val response: String
) {
    fun toOmdbDetail() = OmdbDetail(
        year = this.year,
        genre = this.genre,
        director = this.director,
        writer = this.writer,
        actors = this.actors,
        plot = this.plot,
        imdbRating = this.imdbRating
    )
}
