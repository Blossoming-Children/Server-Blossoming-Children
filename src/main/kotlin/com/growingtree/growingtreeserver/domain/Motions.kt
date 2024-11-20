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

@Entity(name = "Motions")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "Motions")
data class Motions(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    val userId: Long,
    @Column
    val walk: Int = 0,
    @Column
    val run: Int = 0,
    @Column
    val clap: Int = 0,
    @Column
    val jump: Int = 0,
    @Column
    val sit: Int = 0,
    @Column
    val cross_arms: Int = 0,
    @Column
    val shake_hands: Int = 0,
)
