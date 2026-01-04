package com.example.habit_tracker.database.dao

import com.example.habit_tracker.database.entity.Habit
import org.springframework.data.jpa.repository.JpaRepository

interface HabitRepository : JpaRepository<Habit, Long> {
    fun findAllByUserId(userId: Long): List<Habit>
}