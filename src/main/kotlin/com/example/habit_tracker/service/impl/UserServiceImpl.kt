package com.example.habit_tracker.service.impl

import com.example.habit_tracker.database.dao.UserRepository
import com.example.habit_tracker.model.dto.UserRegistrarionDTO
import com.example.habit_tracker.model.dto.UserResponseDTO
import com.example.habit_tracker.model.mapper.toDto
import com.example.habit_tracker.model.mapper.toEntity
import com.example.habit_tracker.service.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun getUsers(): List<UserResponseDTO> =
        userRepository.findAll().map { it.toDto() }

    override fun saveUser(user: UserRegistrarionDTO): UserResponseDTO =
        userRepository.save(user.toEntity(fakeHashPassword(user.password))).toDto()

    override fun deleteUser(id: Long) = userRepository.deleteById(id)

    override fun getUserById(id: Long): UserResponseDTO {
        val user = userRepository.findByIdOrNull(id)

        if (user != null) return user.toDto()
        else throw RuntimeException("User with id $id not found")
    }

    override fun updateUser(id: Long, user: UserRegistrarionDTO): UserResponseDTO {
        val existingUser = userRepository.findByIdOrNull(id)
        if (existingUser != null) {
            existingUser.name = user.name
            existingUser.email = user.email
            existingUser.passwordHash = fakeHashPassword(user.password)
        }
        else throw RuntimeException("User with id $id not found")
        return userRepository.save(existingUser).toDto()
    }
}