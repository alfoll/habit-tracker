package com.example.habit_tracker.service.impl

import com.example.habit_tracker.database.dao.HabitLogRepository
import com.example.habit_tracker.database.dao.HabitRepository
import com.example.habit_tracker.model.dto.HabitLogDTO
import com.example.habit_tracker.model.dto.StatsDTO
import com.example.habit_tracker.model.mapper.toDto
import com.example.habit_tracker.model.mapper.toEntity
import com.example.habit_tracker.service.HabitLogService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class HabitLogServiceImpl (
    private val habitLogRepository: HabitLogRepository,
    private val habitRepository: HabitRepository
) : HabitLogService {
    override fun createLog(log: HabitLogDTO): HabitLogDTO {
        val date = log.date ?: LocalDate.now()

        val habit = habitRepository.findByIdOrNull(log.habitId)
        if (habit == null) throw RuntimeException("Habit with id ${log.habitId} was not found")

        val existingLog = habitLogRepository.findLogByHabitIdAndDate(log.habitId, date)

        if (existingLog != null) {
            existingLog.done = log.done
            return habitLogRepository.save(existingLog).toDto()
        }
        return habitLogRepository.save(log.toEntity(habit)).toDto()
    }

    override fun getStatsByHabitId(habitId: Long): StatsDTO {
        val habit = habitRepository.findByIdOrNull(habitId)

        if (habit == null) throw RuntimeException("Habit with id $habitId was not found")

        val list = habitLogRepository.findLogByHabitIdAndDateBetween(habitId,
            habit.createdAt.toLocalDate(),
            LocalDate.now())
            .filter { it.done }

        return StatsDTO(habitId, habit.createdAt.toLocalDate(), LocalDate.now(), list.count())
    }

    override fun getStatsByHabitIdInPeriod(habitId: Long, from: LocalDate, to: LocalDate): StatsDTO {
        if (from > to) throw RuntimeException("From ($from) must be less than To ($to)")

        val list = habitLogRepository.findLogByHabitIdAndDateBetween(habitId, from, to)
            .filter { it.done }

        return StatsDTO(habitId, from, to, list.count())
    }
}