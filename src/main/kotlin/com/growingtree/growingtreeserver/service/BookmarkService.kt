package com.growingtree.growingtreeserver.service

interface BookmarkService {
    fun postBookmark(userId: Long, eduId: Long)
    fun deleteBookmark(userId: Long, eduId: Long)
}