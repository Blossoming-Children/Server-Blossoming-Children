package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Goals
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface GoalsRepository : JpaRepository<Goals, Long> {
    @Query("select g from Goals g where g.userId = :userId")
    fun findGoalsByUserId(userId: Long): List<Goals>

    @Query("select g from Goals g where g.userId = :userId and g.targetStamps = :targetStamps")
    fun findGoalsByUserIdAndTargetStamps(userId: Long, targetStamps: Int): Goals?

    @Modifying
    @Query("update Goals set detail = :detail where id = :goalId")
    fun updateGoalsById(goalId: Long, detail: String)
}
