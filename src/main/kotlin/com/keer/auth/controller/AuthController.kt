package com.keer.auth.controller

import com.keer.auth.models.User
import com.keer.auth.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
@CrossOrigin
class AuthController(private val userService: UserService) {
    @PostMapping("register")
    fun register(@RequestBody user: User): ResponseEntity<User> = ResponseEntity.ok(this.userService.save(user))
}