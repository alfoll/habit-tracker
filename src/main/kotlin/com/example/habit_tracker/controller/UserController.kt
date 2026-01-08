package com.example.habit_tracker.controller

import com.example.habit_tracker.model.dto.UserRegistrationDTO
import com.example.habit_tracker.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/users"])
class UserController (
    private val userService: UserService
) {
    @GetMapping
    fun getUsers() = userService.getUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long) = userService.getUserById(id)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable("id") id: Long, @RequestBody body: UserRegistrationDTO) = userService.updateUser(id, body)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Long) = userService.deleteUser(id)
}