package com.HiGreen.backend.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
@Table(name = "auth_sessions")
data class AuthSession(
     @Id
     val deviceId : String

     @Column(name = "user_id", nullable = false)
     var userId : Long

     @Column(name = "jwt_token", nullable = false)
     var jwtToken : String

     @Column(name = "expires_at", nullable = false)
     var expiresAt : String

     @Column(name = "created_at", nullable = false)
     val createdAt : String
)