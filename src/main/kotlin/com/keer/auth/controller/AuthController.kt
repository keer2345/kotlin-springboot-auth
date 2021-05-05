package com.keer.auth.controller

import com.keer.auth.dtos.UserLoginDTO
import com.keer.auth.dtos.UserRegisterDTO
import com.keer.auth.models.User
import com.keer.auth.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("api")
@CrossOrigin
class AuthController(private val userService: UserService) {

    @PostMapping("register")
    fun register(@RequestBody body: UserRegisterDTO): ResponseEntity<User> {
        val encodePassword = BCryptPasswordEncoder().encode(body.password)
        val user: User = User(
            name = body.name,
            email = body.email,
            password = encodePassword
        )

        return ResponseEntity.ok(this.userService.save(user))
    }

    @PostMapping("login")
    fun login(@RequestBody body: UserLoginDTO, response: HttpServletResponse): ResponseEntity<Any> {
        val user = this.userService.findByEmail(body.email)
            ?: return ResponseEntity.badRequest().body("User not found!")

        if (!user.comparePassword(body.password)) {
            return ResponseEntity.badRequest().body("Invalid password...")
        }

        return ResponseEntity.ok("Login success!")
    }
}