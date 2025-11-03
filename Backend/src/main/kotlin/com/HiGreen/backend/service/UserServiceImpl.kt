package com.HiGreen.backend.service

import com.HiGreen.backend.model.User
import com.HiGreen.backend.model.UserType
import com.HiGreen.backend.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun createUser(user: User): User {
        // Encrypt password before saving
        val encodedPassword = passwordEncoder.encode(user.password)

        val newUser = user.copy(
            password = encodedPassword,
            registeredAt = Instant.now().toString(),
            role = user.role.ifBlank { UserType.CITIZEN.name },
            lastLogin = ""
        )

        return userRepository.save(newUser)
    }

    override fun updateUser(userId: String, updatedUser: User): User? {
        val user = userRepository.findById(userId.toLong())
        if (user.isEmpty) return null

        val existing = user.get()
        val updated = existing.copy(
            fullName = updatedUser.fullName,
            email = updatedUser.email,
            phone = updatedUser.phone,
            address = updatedUser.address,
            role = updatedUser.role
        )
        return userRepository.save(updated)
    }

    override fun deleteUser(userId: Long): Boolean {
        return if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId)
            true
        } else false
    }

    override fun getUserById(userId: Long): User? {
        return userRepository.findById(userId).orElse(null)
    }

    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }
}
