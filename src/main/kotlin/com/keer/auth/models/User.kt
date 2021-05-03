package com.keer.auth.models

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
    var password: String = ""
)