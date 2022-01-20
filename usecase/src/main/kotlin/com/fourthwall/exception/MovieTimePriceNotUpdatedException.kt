package com.fourthwall.exception

class MovieTimePriceNotUpdatedException(movieId: Long, movieRoomScheduleId: Long) :
    Exception("The movieId $movieId and movieRoomScheduler $movieRoomScheduleId was not found")
