package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Videos
import org.springframework.data.jpa.repository.JpaRepository

interface VideosRepository : JpaRepository<Videos, Long> {
    fun findVideosById(id: Long): Videos
}
