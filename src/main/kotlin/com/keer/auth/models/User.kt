package com.keer.auth.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?=1L,
    @Column
    val name: String = "",
    @Column(unique = true)
    val email: String = "",
    @Column
    @JsonIgnore
    val password: String = ""
) {
    fun comparePassword(password: String): Boolean =
        BCryptPasswordEncoder().matches(password, this.password)
}