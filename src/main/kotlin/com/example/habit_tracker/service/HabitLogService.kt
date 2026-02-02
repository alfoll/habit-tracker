package com.example.habit_tracker.service

import com.example.habit_tracker.model.dto.HabitLogDTO
import java.time.LocalDate

interface HabitLogService{
    fun createLog(habitId: Long, log: HabitLogDTO, email: String): HabitLogDTO
    fun getLogsByHabitId(habitId: Long, email: String): List<HabitLogDTO>
    fun getLogsByHabitIdInPeriod(habitId: Long, from: LocalDate, to: LocalDate, email: String): List<HabitLogDTO>
}