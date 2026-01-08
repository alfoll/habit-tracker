package com.example.habit_tracker.controller

import com.example.habit_tracker.model.dto.AuthRequestDTO
import com.example.habit_tracker.model.dto.AuthResponseDTO
import com.example.habit_tracker.model.dto.UserRegistrationDTO
import com.example.habit_tracker.service.AuthService
import com.example.habit_tracker.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController (
    private val authService: AuthService,
    private val userService: UserService,
) {
    @PostMapping("/register")
    fun register(@RequestBody userRegister: UserRegistrationDTO): AuthResponseDTO {
        userService.saveUser(userRegister)

        val auth = authService
            .authentication(AuthRequestDTO(
                userRegister.email,
                userRegister.password,)
            )
        return auth
    }

    @PostMapping("/login")
    fun authenticate(@RequestBody authRequest: AuthRequestDTO) : AuthResponseDTO =
        authService.authentication(authRequest)
}