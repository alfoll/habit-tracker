package com.example.habit_tracker.service

import com.example.habit_tracker.model.dto.HabitDTO

interface HabitService {
    fun createHabit(habit: HabitDTO): HabitDTO
    fun updateHabit(id: Long, habit: HabitDTO): HabitDTO

    fun deleteHabit(id: Long)

    fun getUsersHabits(userId: Long): List<HabitDTO>
    fun getHabitById(id: Long): HabitDTO
}