package com.example.habit_tracker.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    // пользователь уже существует
    @ExceptionHandler(UserAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    fun handleExistingUser(e: UserAlreadyExistsException): String {
        return e.message ?: "User already exists"
    }

    // ошибки валидации
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    fun handleValidation(e: MethodArgumentNotValidException): String {
        return "Invalid input"
    }

    // неверные данные входа
    @ExceptionHandler(InvalidCredentialsException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    fun handleInvalidCredentials(e: InvalidCredentialsException): String {
        return e.message ?: "Invalid credentials"
    }

    // не нашлось
    @ExceptionHandler(EntityNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    fun handleEntityNotFound(e: EntityNotFoundException): String {
        return e.message ?: "Entity not found"
    }

    // просто чушь какая то в запросе
    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    fun handleBadRequest(e: BadRequestException): String {
        return e.message ?: "Bad request"
    }

    // остальной рандом
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    fun handleException(e: Exception): String {
        return "Internal server error"
    }
}