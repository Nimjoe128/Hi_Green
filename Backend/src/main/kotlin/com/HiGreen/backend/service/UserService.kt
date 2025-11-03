package com.HiGreen.backend.service

import com.HiGreen.backend.model.User

interface UserService {
    fun createUser(user: User): User
    fun updateUser(userId: String, updatedUser: User): User?
    fun deleteUser(userId: Long): Boolean
}