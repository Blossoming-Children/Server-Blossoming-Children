package com.growingtree.growingtreeserver.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "Users")
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String,
    @Column
    val email: String,
    @Column
    val password: String,
    @Column
    val name: String = "새싹",
    @Column
    val stampCount: Int,
    @Column
    val createdAt: Date,
    @Column
    val updatedAt: Date,
)
