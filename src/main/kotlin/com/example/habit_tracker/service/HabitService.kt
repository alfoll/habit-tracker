package com.example.habit_tracker.service

import com.example.habit_tracker.model.dto.HabitRegistrationDTO
import com.example.habit_tracker.model.dto.HabitResponseDTO

interface HabitService {
    fun createHabit(habit: HabitRegistrationDTO): HabitResponseDTO
    fun updateHabit(id: Long, habit: HabitRegistrationDTO): HabitResponseDTO

    fun deleteHabit(id: Long)

    fun getUsersHabits(userId: Long): List<HabitResponseDTO>
    fun getHabitById(id: Long): HabitResponseDTO
}