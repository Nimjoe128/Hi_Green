package com.HiGreen.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "devices")
data class Device(
    @Id
    val device_id: Long,

    @Column(name = "device_name", nullable = false)
    var deviceName: String,

    @Column(name = "device_type", nullable = false)
    var deviceType: String,

    @Column(name = "location", nullable = false)
    var location: String,

    @Column(name = "status", nullable = false)
    var status: String,

    @Column(name = "last_active", nullable = false)
    var lastActive: String,

    @Column(name = "device_fingerprint", nullable = false)
    var deviceFingerprint: String,

    @Column(name = "user_id", nullable = false)
    var userId: Long
)