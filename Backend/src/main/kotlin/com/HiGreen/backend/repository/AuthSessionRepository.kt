package com.HiGreen.backend.repository

import com.HiGreen.backend.model.AuthSession
import org.springframework.data.jpa.repository.JpaRepository

interface AuthSessionRepository : JpaRepository<AuthSession, String> {
    fun existsByJwtToken(jwtToken: String): Boolean
}
