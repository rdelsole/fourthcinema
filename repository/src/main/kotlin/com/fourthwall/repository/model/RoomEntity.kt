package com.fourthwall.repository.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * This entity represents the characteristics of cinema rooms
 */
@Entity
@Table(name = "rooms")
data class RoomEntity(

    @Id
    @Column(name = "number")
    val number: Int,

    @Column
    val capacity: Int

    // Here could have more infos about rooms, like 3D, soundsystems, etc
)
