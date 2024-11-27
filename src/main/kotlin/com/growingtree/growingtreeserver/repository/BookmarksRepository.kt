package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Bookmarks
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookmarksRepository : JpaRepository<Bookmarks, Long> {
    @Query("select g from Bookmarks g where g.userId = :userId")
    fun findBookmarksByUserId(userId: Long): List<Bookmarks>? = listOf()

    @Query("select g from Bookmarks g where g.userId = :userId and g.eduId = :eduId")
    fun findBookmarksByUserIdAAndEduId(userId: Long, eduId: Long,): Bookmarks?
}
