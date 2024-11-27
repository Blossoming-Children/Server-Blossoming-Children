package com.growingtree.growingtreeserver.controller

import com.growingtree.growingtreeserver.exception.messages.SuccessMessage
import com.growingtree.growingtreeserver.exception.responses.BaseResponse
import com.growingtree.growingtreeserver.service.VideoService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
class VideoController(
    val videoService: VideoService,
) {
    @GetMapping("")
    fun getVideoList(
        @RequestHeader("Authorization") userId: Long,
    ): BaseResponse<*> {
        val getEducationsResponse = videoService.getEducations(userId)
        return BaseResponse.of(SuccessMessage.SUCCESS_GET_VIDEO, getEducationsResponse)
    }

    @GetMapping("/{eduId}")
    fun getVideoDetail(
        @PathVariable eduId: Long,
    ): BaseResponse<*> {
        val getVideosResponse = videoService.getVideos(eduId)
        return BaseResponse.of(SuccessMessage.SUCCESS_GET_VIDEO, getVideosResponse)
    }
}
