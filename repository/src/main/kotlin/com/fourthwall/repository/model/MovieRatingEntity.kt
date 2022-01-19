package com.fourthwall.repository.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "movies_rating")
data class MovieRatingEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long?,

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    val movie: MovieEntity,

    @Column(name = "rating_star", updatable = false, nullable = false)
    val ratingStar: Int
)
