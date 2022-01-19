package com.fourthwall.repository.model

import com.fourthwall.entity.Movie
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "movies")
data class MovieEntity(

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long?,

    @Column(name = "imdb_id", unique = true)
    val imdbId: String?,

    @Column(name = "title", unique = true)
    val title: String?
) {
    fun toMovie() =
        Movie(
            this.id!!,
            this.imdbId!!,
            this.title!!
        )
}
