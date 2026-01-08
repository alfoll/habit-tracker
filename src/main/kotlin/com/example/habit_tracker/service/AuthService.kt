package com.example.habit_tracker.service

import com.example.habit_tracker.model.dto.AuthRequestDTO
import com.example.habit_tracker.model.dto.AuthResponseDTO

interface AuthService {
    fun authentication(authRequest: AuthRequestDTO): AuthResponseDTO
}