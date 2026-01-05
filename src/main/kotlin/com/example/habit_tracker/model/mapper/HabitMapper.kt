package com.example.habit_tracker.model.mapper

import com.example.habit_tracker.database.entity.Habit
import com.example.habit_tracker.database.entity.User
import com.example.habit_tracker.model.dto.HabitRegistrationDTO
import com.example.habit_tracker.model.dto.HabitResponseDTO

fun Habit.toDto() = HabitResponseDTO(
    id = id,
    title = title,
    createdAt = createdAt,
    userId = user.id,
)

fun HabitRegistrationDTO.toEntity(user: User): Habit {
    val entity = Habit(
        title = title,)
    entity.user = user
    return entity
}