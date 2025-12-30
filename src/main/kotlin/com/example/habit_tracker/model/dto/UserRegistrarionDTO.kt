package com.example.habit_tracker.model.dto

data class UserRegistrarionDTO(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
)