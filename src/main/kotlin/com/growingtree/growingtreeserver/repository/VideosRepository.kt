package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Videos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface VideosRepository : JpaRepository<Videos, Long> {
    fun findVideosById(id: Long): Videos

    @Query("select g from Videos g where g.eduId = :eduId")
    fun findVideosByEduId(eduId: Long): List<Videos>
}
