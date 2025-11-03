package com.HiGreen.backend.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date

@Component
class JwtUtil(
    @Value("\${jwt.secret}")
    private val secret : String

    @Valeu("\${jwt.expirationMs}")
    private val expirationMs : Long
){
    private val key : Key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(userId: Long, role: String): String {
        val now = Date()
        val expiryDate = Date(now.time + expirationMs)

        return Jwts.builder()
            .setSubject(userId.toString())
            .claim("role", role)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean{
        return try{
            Jwts.parseBuilder().setSigningKey(key).build().parseClaimsJws(token)
            true
        }catch (ex : Exception){
            false
        }
    }

    fun getUserIdFromToken(token: String): Long {
        val claims = Jwts.parseBuilder().setSigningKey(key).build().parseClaimsJws(token).body
        return claims.subject.toLong()
    }

    fun getExpirationFromToken(token: String): Date {
        val claims = Jwts.parseBuilder().setSigningKey(key).build().parseClaimsJws(token).body
        return claims.expiration
    }
}