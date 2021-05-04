package com.keer.auth.services

import com.keer.auth.models.User
import com.keer.auth.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun save(user: User): User = this.userRepository.save(user)
    fun findByEmail(email: String): User? = this.userRepository.findByEmail(email)
}
