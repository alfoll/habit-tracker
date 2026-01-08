package com.example.habit_tracker.controller

import com.example.habit_tracker.model.dto.AuthRequestDTO
import com.example.habit_tracker.model.dto.AuthResponseDTO
import com.example.habit_tracker.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController (
    private val authService: AuthService
) {
    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthRequestDTO) : AuthResponseDTO =
        authService.authentication(authRequest)
}