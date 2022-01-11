package com.fourthwall.repository.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "movies")
data class Movie(

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,

    @Column(name = "imdb_id", unique = true)
    val imdbId: String,

    @Column(name = "title", unique = true)
    val title: String
)
