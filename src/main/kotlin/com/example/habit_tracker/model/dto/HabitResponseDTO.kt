package com.example.habit_tracker.model.dto

import java.time.LocalDateTime

data class HabitResponseDTO (
    val id: Long,
    val title: String,
    val createdAt: LocalDateTime,

    val doneCount: Int = 0,
)