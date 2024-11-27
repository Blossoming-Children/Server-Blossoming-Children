package com.growingtree.growingtreeserver.controller

import com.growingtree.growingtreeserver.exception.messages.SuccessMessage
import com.growingtree.growingtreeserver.exception.responses.BaseResponse
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
class VideoController {
//    @GetMapping("")
//    fun getVideoList(
//        @RequestHeader("Authorization") userId: Long,
//    ): BaseResponse<*> {
//        val getUserInfoResponse = myPageService.getUserProfile(userId)
//        return BaseResponse.of(SuccessMessage.SUCCESS_GET_PROFILE, getUserInfoResponse)
//    }
}