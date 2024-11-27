package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.domain.Bookmarks
import com.growingtree.growingtreeserver.exception.CustomException
import com.growingtree.growingtreeserver.exception.messages.ErrorMessage
import com.growingtree.growingtreeserver.repository.BookmarksRepository
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class BookmarkServiceImpl(
    private val bookmarksRepository: BookmarksRepository,
) : BookmarkService {
    @Transactional
    override fun postBookmark(userId: Long, eduId: Long) {
        if (isBookmarkExist(userId, eduId)) throw CustomException(ErrorMessage.FAILED_POST_BOOKMARK)
        try {
            bookmarksRepository.save(Bookmarks(userId = userId, eduId = eduId))
        } catch (e: Exception) {
            e.printStackTrace()
            throw CustomException(ErrorMessage.FAILED_POST_BOOKMARK)
        }
    }

    override fun deleteBookmark(userId: Long, eduId: Long) {
        TODO("Not yet implemented")
    }

    private fun isBookmarkExist(userId: Long, eduId: Long): Boolean =
        bookmarksRepository.findBookmarksByUserIdAndEduId(userId, eduId) != null

}
