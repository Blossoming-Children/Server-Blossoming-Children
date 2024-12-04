package com.growingtree.growingtreeserver.dto.video.request

data class PatchAchievementRequest(
    val eduId: Long,
    val progress: Int,
)
