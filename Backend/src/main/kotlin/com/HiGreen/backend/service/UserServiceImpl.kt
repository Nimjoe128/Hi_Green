package com.HiGreen.backend.service
import com.HiGreen.backend.model.User
import com.HiGreen.backend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun createUser(user:User): User {
        val randomPart = UUID.randomUUID().toString().substring(0, 8)
        val timestampPart = System.currentTimeMillis().toString().takeLast(8)
        val role = UserType.CITIZEN.name

        val newUser = user.copy(
            user_id = "USR-$randomPart-$timestampPart",
            registered_at = System.currentTimeMillis().toString(),
            role = role
        )
        return userRepository.save(newUser)

    }
}