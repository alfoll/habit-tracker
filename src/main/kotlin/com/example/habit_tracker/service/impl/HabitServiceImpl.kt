package com.example.habit_tracker.service.impl

import com.example.habit_tracker.database.dao.HabitRepository
import com.example.habit_tracker.database.dao.UserRepository
import com.example.habit_tracker.model.dto.HabitRegistrationDTO
import com.example.habit_tracker.model.dto.HabitResponseDTO
import com.example.habit_tracker.model.mapper.toDto
import com.example.habit_tracker.model.mapper.toEntity
import com.example.habit_tracker.service.HabitService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class HabitServiceImpl (
    private val habitRepository: HabitRepository,
    private val userRepository: UserRepository,
) : HabitService {
    override fun createHabit(habit: HabitRegistrationDTO): HabitResponseDTO {
        val user = userRepository.findById(habit.userId).get()
        return habitRepository.save(habit.toEntity(user)).toDto()
    }

    override fun deleteHabit(id: Long) = habitRepository.deleteById(id)

    override fun getHabitById(id: Long): HabitResponseDTO {
        val habit = habitRepository.findByIdOrNull(id)
        if (habit != null) return habit.toDto()
        else throw RuntimeException("Habit with id $id was not found")
    }

    override fun updateHabit(id: Long, habit: HabitRegistrationDTO): HabitResponseDTO {
        val existingHabit = habitRepository.findByIdOrNull(id)
        if (existingHabit != null) {
            existingHabit.title = habit.title
        }
        else throw RuntimeException("Habit with id $id was not found")
        return habitRepository.save(existingHabit).toDto()
    }

    override fun getUsersHabits(userId: Long): List<HabitResponseDTO> {
//        val user = userRepository.findById(userId).get()
        return habitRepository.findAllByUserId(userId).map { it.toDto() }
    }
}