package com.example.habit_tracker.database.dao

import com.example.habit_tracker.database.entity.HabitLog
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface HabitLogRepository : JpaRepository<HabitLog, Long> {
    fun findLogByHabitIdAndDate(habitId: Long, date: LocalDate): HabitLog?
    fun findLogByHabitIdAndDateBetween(habitId: Long, from: LocalDate, to: LocalDate): List<HabitLog>
}