package com.fourthwall.repository.model

import java.math.BigDecimal
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
data class MovieRoomSchedule(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,

    @OneToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    val movie: Movie,

    @OneToOne
    @JoinColumn(name = "room_number", referencedColumnName = "number")
    val room: Room,

    @Column
    val price: BigDecimal,

    @Column
    val hour: Int
)
