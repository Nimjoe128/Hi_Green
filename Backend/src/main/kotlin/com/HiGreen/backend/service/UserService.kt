package com.HiGreen.backend.service

import com.HiGreen.backend.model.User

interface UserService {
    fun createUser(user: User): User
    fun getUserById(userId: Long): User?
    fun getAllUsers(): List<User>
    fun updateUser(userId: Long, updatedUser: User): User?
    fun deleteUser(userId: Long): Boolean
}