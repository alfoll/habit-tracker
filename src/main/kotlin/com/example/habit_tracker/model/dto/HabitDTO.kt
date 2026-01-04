package com.example.habit_tracker.model.dto

import java.time.LocalDateTime

data class HabitDTO (
    val id: Long,
    val title: String,
    val createdAt: LocalDateTime,
    val userId: Long,
)