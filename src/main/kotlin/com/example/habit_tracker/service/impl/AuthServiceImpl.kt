package com.example.habit_tracker.service.impl

import com.example.habit_tracker.config.JwtProperties
import com.example.habit_tracker.model.dto.AuthRequestDTO
import com.example.habit_tracker.model.dto.AuthResponseDTO
import com.example.habit_tracker.service.AuthService
import com.example.habit_tracker.service.CustomUserDetailsService
import com.example.habit_tracker.service.TokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthServiceImpl (
    private val authManager: AuthenticationManager,
    private val tokenService: TokenService,
    private val userDetailsService: CustomUserDetailsService,
    private val jwtProperties: JwtProperties,
) : AuthService {
    
    override fun authentication(authRequest: AuthRequestDTO): AuthResponseDTO {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password),
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)
        val accessToken = tokenService.generate(
            user,
            Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration))

        return AuthResponseDTO(accessToken)
    }
}