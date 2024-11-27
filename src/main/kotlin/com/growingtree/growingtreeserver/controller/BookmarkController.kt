package com.growingtree.growingtreeserver.controller

import com.growingtree.growingtreeserver.exception.messages.SuccessMessage
import com.growingtree.growingtreeserver.exception.responses.BaseResponse
import com.growingtree.growingtreeserver.service.BookmarkService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/bookmark")
@RequiredArgsConstructor
class BookmarkController(
    val bookmarkService: BookmarkService,
) {
    @PostMapping("{eduId}")
    fun postBookmark(
        @RequestHeader("Authorization") userId: Long,
        @PathVariable("eduId") eduId: Long,
    ): BaseResponse<*> {
        bookmarkService.postBookmark(userId, eduId)
        return BaseResponse.of(SuccessMessage.SUCCESS_POST_BOOKMARK)
    }

    @DeleteMapping("{eduId}")
    fun deleteBookmark(
        @RequestHeader("Authorization") userId: Long,
        @PathVariable("eduId") eduId: Long,
    ): BaseResponse<*> {
        bookmarkService.deleteBookmark(userId, eduId)
        return BaseResponse.of(SuccessMessage.SUCCESS_DELETE_BOOKMARK)
    }
}
