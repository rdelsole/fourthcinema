package com.fourthwall.repository.jpa

import com.fourthwall.repository.model.MovieRoomSchedule
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MovieRoomScheduleJPARepository : CrudRepository<MovieRoomSchedule, UUID> {

    @Query("select m from MovieRoomSchedule m where m.movie.id = ?1")
    fun findByMovieId(id: Long): List<MovieRoomSchedule>
}
