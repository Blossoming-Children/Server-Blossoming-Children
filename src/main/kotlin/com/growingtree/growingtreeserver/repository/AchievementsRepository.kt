package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Achievements
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AchievementsRepository : JpaRepository<Achievements, Long> {
    @Query("select g from Achievements g where g.userId = :userId and g.eduId = :eduId")
    fun getAchievementByUserIdAndEduId(
        userId: Long,
        eduId: Long,
    ): Achievements?

    @Query("update Achievements g set g.progress = :progress where g.userId = :userId and g.eduId = :eduId")
    fun updateProgressByUserIdAndEduId(
        userId: Long,
        eduId: Long,
        progress: Int,
    )
}
