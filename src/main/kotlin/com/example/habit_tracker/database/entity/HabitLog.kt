package com.example.habit_tracker.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "habitLog")
class HabitLog (
    @Column(name = "date", nullable = false)
    val date: LocalDateTime,

    @Column(name = "done")
    val done: Boolean = false
) : AbstractClassEntity() {
    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    lateinit var habit: Habit
}