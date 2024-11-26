package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.dto.video.response.GetEducationDetailResponse
import com.growingtree.growingtreeserver.dto.video.response.GetEducationsResponse

interface VideoService {
    fun getEducations(userId: Long): List<GetEducationsResponse>

    fun getVideos(eduId: Long): List<GetEducationDetailResponse>
}
