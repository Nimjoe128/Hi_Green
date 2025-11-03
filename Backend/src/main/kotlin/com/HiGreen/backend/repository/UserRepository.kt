package com.HiGreen.backend.repository

import com.HiGreen.backend.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?

    fun findByPhone(phone: String): User?

    fun existsByEmail(email: String): Boolean

    fun existsByPhone(phone: String): Boolean
}
