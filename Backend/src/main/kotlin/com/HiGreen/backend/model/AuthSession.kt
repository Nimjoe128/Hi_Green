package com.HiGreen.backend.model

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "auth_sessions")
data class AuthSession(
    @Id
    @Column(name = "device_id", nullable = false)
    val deviceId: String,

    @Column(name = "user_id", nullable = false)
    var userId: Long,

    @Column(name = "jwt_token", nullable = false, length = 2048)
    var jwtToken: String,

    @Column(name = "expires_at", nullable = false)
    var expiresAt: Instant,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant = Instant.now()
)
