package com.example.habit_tracker.service

import com.example.habit_tracker.database.dao.UserRepository
import com.example.habit_tracker.model.mapper.toUserDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService (
    private val userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findUserByEmail(username)?.toUserDetails()
            ?: throw UsernameNotFoundException("User with username $username not found")
}