package com.HiGreen.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "auth_sessions")
data class AuthSession(
    @Id
    @Column(name = "device_id", nullable = false)
    val deviceId: String,

    @Column(name = "user_id", nullable = false)
    var userId: Long,

    @Column(name = "jwt_token", nullable = false)
    var jwtToken: String,

    @Column(name = "expires_at", nullable = false)
    var expiresAt: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: String
)
