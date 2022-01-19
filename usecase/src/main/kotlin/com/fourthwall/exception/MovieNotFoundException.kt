package com.fourthwall.exception

class MovieNotFoundException(val movieId: Long?) : Exception("The movieId $movieId was not found")
