package com.example.habit_tracker.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "habitLog")
class HabitLog (
    @Column(name = "date", nullable = false)
    val date: LocalDate,

    @Column(name = "done")
    var done: Boolean = false
) : AbstractClassEntity() {
    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    lateinit var habit: Habit
}