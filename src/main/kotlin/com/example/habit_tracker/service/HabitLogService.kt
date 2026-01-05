package com.example.habit_tracker.service

import com.example.habit_tracker.model.dto.HabitLogDTO
import com.example.habit_tracker.model.dto.StatsDTO
import java.time.LocalDate

interface HabitLogService{
    fun createLog(log: HabitLogDTO): HabitLogDTO
    fun getStatsByHabitId(habitId: Long): StatsDTO
    fun getStatsByHabitIdInPeriod(habitId: Long, from: LocalDate, to: LocalDate): StatsDTO
}