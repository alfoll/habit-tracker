package com.example.habit_tracker.model.mapper

import com.example.habit_tracker.database.entity.Habit
import com.example.habit_tracker.database.entity.HabitLog
import com.example.habit_tracker.model.dto.HabitLogDTO
import java.time.LocalDate

fun HabitLog.toDto() = HabitLogDTO(
    date = date,
    done = done,
    habitId = habit.id,
)

fun HabitLogDTO.toEntity(habit: Habit): HabitLog {
    val entity = HabitLog(
        date = date ?: LocalDate.now(),
        done = done,
    )
    entity.habit = habit
    return entity
}