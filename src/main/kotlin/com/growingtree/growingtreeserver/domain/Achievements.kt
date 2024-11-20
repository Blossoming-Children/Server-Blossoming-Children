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

@Entity(name = "Achievements")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "Achievements")
data class Achievements(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    val userId: Long,
    @Column
    val videoId: Long,
    @Column
    val progress: Int,
    @Column(updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
