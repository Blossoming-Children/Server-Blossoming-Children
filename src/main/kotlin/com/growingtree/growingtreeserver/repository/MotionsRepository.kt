package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Motions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MotionsRepository : JpaRepository<Motions, Long> {
    @Query("select g from Motions g where g.userId = :userId")
    fun findMotionsByUserId(userId: Long): Motions
}
