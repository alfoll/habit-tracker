package com.example.habit_tracker.service.impl

import com.example.habit_tracker.database.dao.HabitRepository
import com.example.habit_tracker.database.dao.UserRepository
import com.example.habit_tracker.database.entity.Habit
import com.example.habit_tracker.model.dto.HabitRegistrationDTO
import com.example.habit_tracker.model.dto.HabitResponseDTO
import com.example.habit_tracker.model.mapper.toDto
import com.example.habit_tracker.model.mapper.toEntity
import com.example.habit_tracker.service.HabitService
import org.springframework.stereotype.Service

@Service
class HabitServiceImpl (
    private val habitRepository: HabitRepository,
    private val userRepository: UserRepository,
) : HabitService {

    private fun getHabitExc(id: Long, email: String): Habit {
        val user = userRepository.findUserByEmail(email)
            ?: throw RuntimeException("User with email $email not found")

//        if (habitRepository.findByIdOrNull(id) == null)
//            throw RuntimeException("Habit with id $id was not found")

        return habitRepository.findByIdAndUserId(id, user.id)
            ?: throw RuntimeException("Habit with id $id was not found or does not belong to this user")
    }

    override fun createHabit(habit: HabitRegistrationDTO, email: String): HabitResponseDTO {
        val user = userRepository.findUserByEmail(email)
            ?: throw RuntimeException("User with email $email not found")
        return habitRepository.save(habit.toEntity(user)).toDto()
    }

    override fun deleteHabit(id: Long, email: String) {
        val existingHabit = getHabitExc(id, email)
        return habitRepository.delete(existingHabit)
    }

    override fun getHabitById(id: Long, email: String): HabitResponseDTO =
         getHabitExc(id, email).toDto()


    override fun updateHabit(id: Long, habit: HabitRegistrationDTO, email: String): HabitResponseDTO {
        val existingHabit = getHabitExc(id, email)
        existingHabit.title = habit.title
        return habitRepository.save(existingHabit).toDto()
    }

    override fun getUsersHabits(email: String): List<HabitResponseDTO> {
        val user = userRepository.findUserByEmail(email)
            ?: throw RuntimeException("User with email $email not found")
        return habitRepository.findAllByUserId(user.id).map { it.toDto() }
    }
}