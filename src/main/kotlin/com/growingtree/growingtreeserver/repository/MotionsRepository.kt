package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Motions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface MotionsRepository : JpaRepository<Motions, Long> {
    @Query("select g from Motions g where g.userId = :userId")
    fun findMotionsByUserId(userId: Long): Motions

    @Modifying
    @Query(
        "UPDATE Motions a SET a.walk = CASE WHEN :motion = 'walk' THEN a.walk + 1 ELSE a.walk END, " +
            "a.run = CASE WHEN :motion = 'run' THEN a.run + 1 ELSE a.run END," +
            "a.clap = CASE WHEN :motion = 'clap' THEN a.clap + 1 ELSE a.clap END," +
            "a.jump = CASE WHEN :motion = 'jump' THEN a.jump + 1 ELSE a.jump END," +
            "a.sit = CASE WHEN :motion = 'sit' THEN a.sit + 1 ELSE a.sit END," +
            "a.cross_arms = CASE WHEN :motion = 'cross_arms' THEN a.cross_arms + 1 ELSE a.cross_arms END," +
            "a.shake_hands = CASE WHEN :motion = 'shake_hands' THEN a.shake_hands + 1 ELSE a.shake_hands END " +
            "WHERE a.userId = :userId",
    )
    fun updateMotionsByUserId(
        userId: Long,
        motion: String,
    )
}
