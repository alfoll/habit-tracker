package com.example.habit_tracker.model.mapper

import com.example.habit_tracker.database.entity.User
import com.example.habit_tracker.model.dto.UserRegistrarionDTO
import com.example.habit_tracker.model.dto.UserResponseDTO

fun User.toDto() = UserResponseDTO(
    id = id,
    name = name,
    email = email,
)

fun UserRegistrarionDTO.toEntity(encodedPass: String) = User(
    name = name,
    email = email,
    passwordHash = encodedPass,
)