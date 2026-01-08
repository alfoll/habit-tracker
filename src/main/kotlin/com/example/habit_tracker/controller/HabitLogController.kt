package com.example.habit_tracker.controller

import com.example.habit_tracker.model.dto.HabitLogDTO
import com.example.habit_tracker.service.HabitLogService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping(value = ["/api/habits/{habitId}/logs"])
class HabitLogController (
    private val habitLogService: HabitLogService,
) {
    @GetMapping
    fun getStatsByHabitId(@PathVariable("habitId") habitId: Long) = habitLogService.getStatsByHabitId(habitId)

    @GetMapping("/period")
    fun getStatsByHabitIdInPeriod(@PathVariable("habitId") habitId: Long,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) from: LocalDate,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) to: LocalDate) =
        habitLogService.getStatsByHabitIdInPeriod(habitId, from, to)

    @PostMapping
    fun createLog(@RequestBody log: HabitLogDTO) = habitLogService.createLog(log)
}