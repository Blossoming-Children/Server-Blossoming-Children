package com.growingtree.growingtreeserver.exception

import com.growingtree.growingtreeserver.exception.responses.BaseException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CommonException::class)
    fun handleCustomException(e: CommonException): ResponseEntity<BaseException> {
        return ResponseEntity
            .badRequest()
            .body(
                BaseException.of(e.errorMessage),
            )
    }
}
