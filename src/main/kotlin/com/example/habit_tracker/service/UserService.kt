package com.example.habit_tracker.service

import com.example.habit_tracker.model.dto.UserRegistrarionDTO
import com.example.habit_tracker.model.dto.UserResponseDTO

interface UserService {
    fun fakeHashPassword(password: String): String = "hashed_" + password

    fun getUsers(): List<UserResponseDTO>
    fun getUserById(id: Long): UserResponseDTO

    fun saveUser(user: UserRegistrarionDTO): UserResponseDTO
    fun updateUser(id: Long, user: UserRegistrarionDTO): UserResponseDTO

    fun deleteUser(id: Long)
}