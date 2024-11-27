package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.dto.video.response.GetEducationsResponse
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class VideoServiceImpl: VideoService {
    override fun getEducations(userId: Long): List<GetEducationsResponse> {

        TODO("Not yet implemented")
    }
}