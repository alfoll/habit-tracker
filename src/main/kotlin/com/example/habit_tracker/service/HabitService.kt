package com.example.habit_tracker.service

import com.example.habit_tracker.database.entity.Habit
import com.example.habit_tracker.model.dto.HabitRegistrationDTO
import com.example.habit_tracker.model.dto.HabitResponseDTO

interface HabitService {
    // не уверена, нужно и делать ее доступной
    fun getHabitExc(id: Long, email: String): Habit

    fun createHabit(habit: HabitRegistrationDTO, email: String): HabitResponseDTO
    fun updateHabit(id: Long, habit: HabitRegistrationDTO, email: String): HabitResponseDTO

    fun deleteHabit(id: Long, email: String)

    fun getUsersHabits(email: String): List<HabitResponseDTO>
    fun getHabitById(id: Long, email: String): HabitResponseDTO
}