package com.HiGreen.backend.model

import jakarta.persistence.Entity

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val user_id: Long

    @Column(name = "registered_at", nullable = false)
    val registered_at: String

    @Column(name = "email", nullable = false, unique = true)
    var email: String

    @Column(name = "phone", nullable = false, unique = true)
    var phone : String

    @Column(name = "password", nullable = false)
    var password: String

    @Column(name = "full_name", nullable = false)
    var fullName: String

    @Column(name = "address", nullable = false)
    var address: String

    @Column(name = "last_login", nullable = false)
    var lastLogin : String

    @Column(name = "role", nullable = false)
    var role : String
)