package com.example.habit_tracker.controller

import com.example.habit_tracker.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ping")
class PingController (
    private val userService: UserService,
) {
    @GetMapping
    fun ping() : Boolean = userService.ping()
}