package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Achievements
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AchievementsRepository : JpaRepository<Achievements, Long> {
    @Query("select g from Achievements g where g.userId = :userId and g.videoId = :videoId")
    fun getAchievementByUserIdAndVideoId(
        userId: Long,
        videoId: Long,
    ): Achievements?
}
