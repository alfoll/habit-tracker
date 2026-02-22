package com.example.habit_tracker.service.impl

import com.example.habit_tracker.database.dao.UserRepository
import com.example.habit_tracker.exception.EntityNotFoundException
import com.example.habit_tracker.exception.UserAlreadyExistsException
import com.example.habit_tracker.model.dto.UserRegistrationDTO
import com.example.habit_tracker.model.dto.UserResponseDTO
import com.example.habit_tracker.model.mapper.toDto
import com.example.habit_tracker.model.mapper.toEntity
import com.example.habit_tracker.service.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun ping(): Boolean {
        try {
            userRepository.count()
            return true
        } catch (ex: Exception) {
            return false
        }
    }

    override fun getUsers(): List<UserResponseDTO> =
        userRepository.findAll().map { it.toDto() }

    override fun saveUser(user: UserRegistrationDTO): UserResponseDTO {
        val existingUserEmail = userRepository.findUserByEmail(user.email)
        if (existingUserEmail != null) {
            throw UserAlreadyExistsException("User with this email already exists")
        }

        val encodedPassword = passwordEncoder.encode(user.password)!!
        val entity = user.toEntity(encodedPassword)
        return userRepository.save(entity).toDto()
    }

    override fun deleteUser(id: Long) = userRepository.deleteById(id)

    override fun getUserById(id: Long): UserResponseDTO {
        val user = userRepository.findByIdOrNull(id)

        if (user != null)
            return user.toDto()
        else
            throw EntityNotFoundException("User with this id not found")
    }

    override fun updateUser(id: Long, user: UserRegistrationDTO): UserResponseDTO {
        val existingUser = userRepository.findByIdOrNull(id)

        if (existingUser != null) {
            existingUser.name = user.name
            existingUser.email = user.email
            existingUser.passwordHash = passwordEncoder.encode(user.password)!!
        }
        else
            throw EntityNotFoundException("User with this id not found")

        return userRepository.save(existingUser).toDto()
    }

    override fun findUserByEmail(email: String): UserResponseDTO =
        userRepository.findUserByEmail(email)?.toDto()
            ?: throw EntityNotFoundException("User with this email not found")
}