package com.example.habit_tracker.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "`user`")
class User (
    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name  = "email", nullable = false, unique = true)
    val email: String,

    @Column(name = "passwordHash", nullable = false)
    val passwordHash: String
) : AbstractClassEntity () {
}