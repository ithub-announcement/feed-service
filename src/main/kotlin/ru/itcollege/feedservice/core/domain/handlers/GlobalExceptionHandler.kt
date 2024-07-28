package ru.itcollege.feedservice.core.domain.handlers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

class ResourceNotFoundException(message: String) : RuntimeException(message)

@ControllerAdvice
class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException::class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  fun handleResourceNotFound(ex: ResourceNotFoundException): ResponseEntity<String> {
    return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
  }

  @ExceptionHandler(Exception::class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  fun handleGenericException(ex: Exception): ResponseEntity<String> {
    return ResponseEntity.internalServerError().body(ex.message)
  }
}