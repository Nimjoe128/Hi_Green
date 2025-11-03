package com.HiGreen.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: String,

    @Column(name = "registered_at", nullable = false)
    val registeredAt: String,

    @Column(name = "email", nullable = false, unique = true)
    var email: String,

    @Column(name = "phone", nullable = false, unique = true)
    var phone : String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "full_name", nullable = false)
    var fullName: String,

    @Column(name = "address", nullable = false)
    var address: String,

    @Column(name = "last_login", nullable = false)
    var lastLogin : String,

    @Column(name = "role", nullable = false)
    var role : String
)