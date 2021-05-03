package com.keer.auth.models

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    var password: String = ""
)