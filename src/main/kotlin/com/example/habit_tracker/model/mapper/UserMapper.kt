package com.example.habit_tracker.model.mapper

import com.example.habit_tracker.database.entity.User
import com.example.habit_tracker.model.dto.UserRegistrationDTO
import com.example.habit_tracker.model.dto.UserResponseDTO
import org.springframework.security.core.userdetails.UserDetails

typealias SpringUser = org.springframework.security.core.userdetails.User

fun User.toDto() = UserResponseDTO(
    id = id,
    name = name,
    email = email,
)

fun UserRegistrationDTO.toEntity(encodedPass: String) = User(
    name = name,
    email = email,
    passwordHash = encodedPass,
)

fun User.toUserDetails() : UserDetails =
    SpringUser.builder()
        .username(this.email)
        .password(this.passwordHash)
        .roles("USER")
        .build()
