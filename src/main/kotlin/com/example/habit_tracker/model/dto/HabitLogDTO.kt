package com.example.habit_tracker.model.dto

import java.time.LocalDate

data class HabitLogDTO (
    val date: LocalDate? = null,
    var done: Boolean,
    val habitId: Long,
)