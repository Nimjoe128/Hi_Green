package com.HiGreen.backend.model

data class LoginResponse(
    val token: String,
    val tokenType: String = "Bearer"
)