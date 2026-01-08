package com.example.habit_tracker.database.dao

import com.example.habit_tracker.database.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findUserByEmail(email: String): User?
}