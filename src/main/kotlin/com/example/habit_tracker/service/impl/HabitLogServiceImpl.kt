package com.example.habit_tracker.service.impl

import com.example.habit_tracker.database.dao.HabitLogRepository
import com.example.habit_tracker.model.dto.HabitLogDTO
import com.example.habit_tracker.model.mapper.toDto
import com.example.habit_tracker.model.mapper.toEntity
import com.example.habit_tracker.service.HabitLogService
import com.example.habit_tracker.service.HabitService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class HabitLogServiceImpl (
    private val habitLogRepository: HabitLogRepository,
    private val habitService: HabitService
) : HabitLogService {
    override fun createLog(habitId: Long, log: HabitLogDTO, email: String): HabitLogDTO {
        val date = log.date ?: LocalDate.now()

        val habit = habitService.getHabitExc(habitId, email)

        val existingLog = habitLogRepository.findLogByHabitIdAndDate(habitId, date)

        if (existingLog != null) {
            existingLog.done = log.done
            return habitLogRepository.save(existingLog).toDto()
        }
        return habitLogRepository.save(log.toEntity(habit)).toDto()
    }

    override fun getLogsByHabitId(habitId: Long, email: String): List<HabitLogDTO> {
        val habit = habitService.getHabitExc(habitId, email)

        return habitLogRepository.findLogByHabitIdAndDateBetween(habit.id,
            habit.createdAt.toLocalDate(),
            LocalDate.now())
            .map { it.toDto() }
    }

    override fun getLogsByHabitIdInPeriod(habitId: Long, from: LocalDate, to: LocalDate, email: String): List<HabitLogDTO> {
        if (from > to) throw RuntimeException("From ($from) must be less than To ($to)")

        val habit = habitService.getHabitExc(habitId, email)

        return habitLogRepository.findLogByHabitIdAndDateBetween(habit.id, from, to)
            .map { it.toDto() }
    }
}