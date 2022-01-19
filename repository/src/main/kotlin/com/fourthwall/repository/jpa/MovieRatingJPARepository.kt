package com.fourthwall.repository.jpa

import com.fourthwall.repository.model.MovieRatingEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface MovieRatingJPARepository : CrudRepository<MovieRatingEntity, UUID> {

    @Query("select m from MovieRatingEntity m where m.movie.id = ?1")
    fun findByMovieId(id: Long): List<MovieRatingEntity>
}
