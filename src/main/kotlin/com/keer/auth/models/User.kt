package com.keer.auth.models

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?,
    @Column
    var name: String = "",
    @Column(unique = true)
    var email: String = "",
    @Column
//    @JsonIgnore
    var password: String = ""
) {
    fun comparePassword(password: String): Boolean =
        BCryptPasswordEncoder().matches(password, this.password)
}