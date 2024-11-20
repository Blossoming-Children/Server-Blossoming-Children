package com.growingtree.growingtreeserver.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AccessLevel.PROTECTED
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Entity(name = "Videos")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "Videos")
data class Videos(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    val url: String,
    @Column
    val title: String,
    @Column
    val description: String,
    @Column(updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
