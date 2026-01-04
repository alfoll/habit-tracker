package com.example.habit_tracker.controller

import com.example.habit_tracker.model.dto.HabitDTO
import com.example.habit_tracker.service.HabitService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = ["/habits"])
class HabitController (
    private val habitService: HabitService,
) {
    @GetMapping("/{id}")
    fun getHabitById(@PathVariable("id") id: Long): HabitDTO = habitService.getHabitById(id)

    @GetMapping
    fun getUsersHabits(@RequestParam userId: Long): List<HabitDTO> = habitService.getUsersHabits(userId)

    @DeleteMapping("/{id}")
    fun deleteHabit(@PathVariable("id") id: Long) = habitService.deleteHabit(id)

    @PostMapping
    fun createHabit(@RequestBody habit: HabitDTO): HabitDTO = habitService.createHabit(habit)

    @PutMapping("/{id}")
    fun updateHabit(@PathVariable id: Long, @RequestBody habit: HabitDTO): HabitDTO = habitService.updateHabit(id, habit)
}