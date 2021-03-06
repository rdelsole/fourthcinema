package com.fourthwall.repository.model

import java.math.BigDecimal
import java.sql.Time
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "movies_rooms_schedule")
data class MovieRoomScheduleEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long?,

    @OneToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    val movie: MovieEntity,

    @OneToOne
    @JoinColumn(name = "room_number", referencedColumnName = "number")
    val room: RoomEntity,

    @Column
    val price: BigDecimal,

    @Column
    val hour: Time
)
