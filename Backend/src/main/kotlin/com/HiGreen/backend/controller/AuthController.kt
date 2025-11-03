package com.HiGreen.backend.controller

import com.HiGreen.backend.model.AuthSession
import com.HiGreen.backend.model.LoginRequest
import com.HiGreen.backend.model.LoginResponse
import com.HiGreen.backend.repository.AuthSessionRepository
import com.HiGreen.backend.repository.UserRepository
import com.HiGreen.backend.security.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val authSessionRepository: AuthSessionRepository,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        val authToken = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        authenticationManager.authenticate(authToken)

        val token = jwtUtil.generateToken(loginRequest.username)
        val expiryDate = jwtUtil.getExpirationFromToken(token).toInstant()

        // save session
        val user = userRepository.findByEmail(loginRequest.username)
            ?: return ResponseEntity.badRequest().body(mapOf("error" to "User not found"))

        val session = AuthSession(
            deviceId = "device-${user.userId}", // change if you track real device ID
            userId = user.userId,
            jwtToken = token,
            expiresAt = expiryDate
        )
        authSessionRepository.save(session)

        return ResponseEntity.ok(LoginResponse(token))
    }

    @PostMapping("/logout")
    fun logout(@RequestHeader("Authorization") authorization: String?): ResponseEntity<Any> {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(mapOf("error" to "Missing or invalid Authorization header"))
        }

        val token = authorization.substring(7)
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.ok(mapOf("message" to "Logout successful"))
        }

        val session = authSessionRepository.findAll().find { it.jwtToken == token }
        if (session != null) {
            authSessionRepository.delete(session)
        }

        return ResponseEntity.ok(mapOf("message" to "Logout successful"))
    }
}
