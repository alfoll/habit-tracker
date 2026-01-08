package com.example.habit_tracker.service

import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

interface TokenService {
    fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String

    fun getEmail(token: String): String?

    fun isExpired(token: String): Boolean

    fun isValid(token: String, userDetails: UserDetails): Boolean
}