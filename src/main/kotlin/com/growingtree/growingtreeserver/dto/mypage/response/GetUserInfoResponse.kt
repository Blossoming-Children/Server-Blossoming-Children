package com.growingtree.growingtreeserver.dto.mypage.response

import com.growingtree.growingtreeserver.enums.Motion

data class GetUserInfoResponse(
    val nickname: String,
    val motionLearningCount: List<LearningCount>,
    val bookmarkList: List<Bookmark>,
) {
    data class LearningCount(
        val motion: Motion,
        val learningCount: Int,
    )

    data class Bookmark(
        val eduId: Long,
        val title: String,
        val videoUrl: String,
        val achievementRate: Int,
    )
}
