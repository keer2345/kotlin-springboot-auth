package com.keer.auth.controller

import com.keer.auth.models.User
import com.keer.auth.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
@CrossOrigin
class AuthController(private val userService: UserService) {
    @PostMapping("register")
    fun register(@RequestBody user: User): ResponseEntity<User> {
        val encodePassword = BCryptPasswordEncoder().encode(user.password)
        val newUser = user.copy(password = encodePassword)

        return ResponseEntity.ok(this.userService.save(newUser))
    }
}