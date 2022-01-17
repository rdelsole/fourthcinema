package com.fourthwall.entity

import java.math.BigDecimal
import java.time.LocalTime

data class MoviePriceRoomTime(
    val price: BigDecimal,
    val time: LocalTime,
    val roomDetails: Room
)
