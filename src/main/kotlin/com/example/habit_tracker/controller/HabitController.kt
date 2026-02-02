package com.example.habit_tracker.controller

import com.example.habit_tracker.model.dto.HabitRegistrationDTO
import com.example.habit_tracker.model.dto.HabitResponseDTO
import com.example.habit_tracker.service.HabitService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
@RequestMapping(value = ["/api/habits"])
class HabitController (
    private val habitService: HabitService,
) {
    @GetMapping("/{id}")
    fun getHabitById(@PathVariable id: Long, principal: Principal): HabitResponseDTO =
        habitService.getHabitById(id, principal.name)

    @GetMapping
    fun getUsersHabits(principal: Principal): List<HabitResponseDTO> =
        habitService.getUsersHabits(principal.name)

    @DeleteMapping("/{id}")
    fun deleteHabit(@PathVariable id: Long, principal: Principal) =
        habitService.deleteHabit(id, principal.name)

    @PostMapping
    fun createHabit(@RequestBody habit: HabitRegistrationDTO,
                    principal: Principal): HabitResponseDTO =
        habitService.createHabit(habit, principal.name)

    @PutMapping("/{id}")
    fun updateHabit(@PathVariable id: Long, @RequestBody habit: HabitRegistrationDTO, principal: Principal): HabitResponseDTO =
        habitService.updateHabit(id, habit, principal.name)
}