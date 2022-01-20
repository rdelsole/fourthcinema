package com.fourthwall.repository.jpa

import com.fourthwall.repository.model.MovieRoomScheduleEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.sql.Time
import java.util.UUID

@Repository
interface MovieRoomScheduleJPARepository : CrudRepository<MovieRoomScheduleEntity, UUID> {

    @Query("select m from MovieRoomScheduleEntity m where m.movie.id = ?1")
    fun findByMovieId(id: Long): List<MovieRoomScheduleEntity>

    @Modifying
    @Query("update MovieRoomScheduleEntity mrs set mrs.hour = :time where mrs.id = :id and mrs.movie.id = :movieId")
    fun updateMovieTime(@Param("movieId") movieId: Long, @Param("id") movieRoomScheduleId: Long, @Param("time") time: Time): Int

    @Modifying
    @Query("update MovieRoomScheduleEntity mrs set mrs.price = :price where mrs.id = :id and mrs.movie.id = :movieId")
    fun updateMoviePrice(@Param("movieId") movieId: Long, @Param("id") movieRoomScheduleId: Long, @Param("price") price: BigDecimal): Int
}
