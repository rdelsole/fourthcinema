package com.fourthwall.web.config

import com.fourthwall.exception.MovieNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [MovieNotFoundException::class])
    fun movieNotFoundHandler(ex: MovieNotFoundException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(
            ex, null, HttpHeaders(), HttpStatus.NOT_FOUND, request
        )
    }
}
