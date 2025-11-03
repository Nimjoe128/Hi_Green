package com.HiGreen.backend.repository

import com.HiGreen.backend.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>

