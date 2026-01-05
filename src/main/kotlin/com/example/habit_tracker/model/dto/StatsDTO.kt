package com.example.habit_tracker.model.dto

import java.time.LocalDate

data class StatsDTO (
    val habitId: Long,
    val dateFrom: LocalDate,
    val dateTo: LocalDate,
    val doneDays: Int,
)