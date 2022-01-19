package com.fourthwall.repository.jpa

import com.fourthwall.repository.model.MovieRoomScheduleEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MovieRoomScheduleJPARepository : CrudRepository<MovieRoomScheduleEntity, UUID> {

    @Query("select m from MovieRoomScheduleEntity m where m.movie.id = ?1")
    fun findByMovieId(id: Long): List<MovieRoomScheduleEntity>
}
