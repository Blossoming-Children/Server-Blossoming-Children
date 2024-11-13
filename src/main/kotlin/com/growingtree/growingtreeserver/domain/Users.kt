package com.growingtree.growingtreeserver.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "Users")
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    val email: String,
    @Column
    val password: String,
    @Column
    val name: String,
    @Column
    val stampCount: Int = 0,
    @Column(updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
