package com.example.habit_tracker.database.dao

import org.springframework.data.repository.CrudRepository
import com.example.habit_tracker.database.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}