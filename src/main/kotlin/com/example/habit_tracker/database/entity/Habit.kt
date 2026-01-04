package com.example.habit_tracker.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "habit")
class Habit (
    @Column(name = "title")
    var title: String,

    @Column(name = "createdAt", nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()
) : AbstractClassEntity() {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    lateinit var user: User
}