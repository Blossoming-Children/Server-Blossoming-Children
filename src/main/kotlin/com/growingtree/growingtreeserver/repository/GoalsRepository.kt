package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Goals
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface GoalsRepository : JpaRepository<Goals, Long> {
    @Query("select g from Goals g where g.userId = :userId")
    fun findGoalsByUserId(userId: Long): List<Goals>
}
