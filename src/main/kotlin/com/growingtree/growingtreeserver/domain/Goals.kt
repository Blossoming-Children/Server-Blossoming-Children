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

@Entity(name = "Goals")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "Goals")
data class Goals(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column
    val userId: Long,
    @Column
    val targetStamps: Int,
    @Column
    val detail: String,
)
