package com.growingtree.growingtreeserver.dto.video.response

data class GetEducationsResponse(
    val title: String,
    val url: String,
    val isBookmarked: Boolean,
    val achievement: Int,
)
