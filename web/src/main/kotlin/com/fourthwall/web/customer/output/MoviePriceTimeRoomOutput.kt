package com.fourthwall.web.customer.output

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fourthwall.entity.MoviePriceRoomTime
import com.fourthwall.web.config.serialize.MoneySerializer
import java.math.BigDecimal
import java.time.LocalTime

data class MoviePriceTimeRoomOutput(

    @JsonProperty("price")
    @JsonSerialize(using = MoneySerializer::class)
    val price: BigDecimal,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val time: LocalTime,
    val room: RoomOutput
) {
    companion object {
        fun fromMoviePriceRoomTime(moviePriceRoomTime: MoviePriceRoomTime) =
            MoviePriceTimeRoomOutput(
                moviePriceRoomTime.price,
                moviePriceRoomTime.time,
                RoomOutput(
                    moviePriceRoomTime.roomDetails.number,
                    moviePriceRoomTime.roomDetails.capacity
                )
            )
    }
}
