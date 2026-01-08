package com.example.habit_tracker.service

import com.example.habit_tracker.model.dto.UserRegistrationDTO
import com.example.habit_tracker.model.dto.UserResponseDTO

interface UserService {

    fun ping() : Boolean

    fun getUsers(): List<UserResponseDTO>
    fun getUserById(id: Long): UserResponseDTO

    fun saveUser(user: UserRegistrationDTO): UserResponseDTO
    fun updateUser(id: Long, user: UserRegistrationDTO): UserResponseDTO

    fun deleteUser(id: Long)

    fun findUserByEmail(email: String): UserResponseDTO?
}