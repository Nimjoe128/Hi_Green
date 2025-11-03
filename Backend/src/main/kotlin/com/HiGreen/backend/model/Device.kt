package com.HiGreen.backend.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
@Table (name = "devices")
data class Device(
    @Id
    val device_id: Long,

    @Column(name = "device_name", nullable = false)
    var device_name: String,

    @Column(name = "device_type", nullable = false)
    var device_type: String,

    @Column(name = "location", nullable = false)
    var location: String,

    @Column(name = "status", nullable = false)
    var status: String,

    @Column(name = "last_active", nullable = false)
    var last_active: String

    @Column(name = "device_fingerprint", nullable = false)
    var device_fingerprint: String

    @Column(name = "user_id", nullable = false)
    var user_id: Long
)