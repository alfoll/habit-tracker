package com.example.habit_tracker.model.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserRegistrationDTO(
    val name: String,

    @field:Email(message = "Invalid email")
    @field:NotBlank(message = "Empty email")
    val email: String,

    @field:NotBlank(message = "Empty password")
    @field:Size(min = 8, message = "Short password")
    val password: String,
)