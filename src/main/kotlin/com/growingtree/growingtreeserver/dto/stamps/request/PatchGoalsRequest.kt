package com.growingtree.growingtreeserver.dto.stamps.request

data class PatchGoalsRequest(
    val targetStamp: Int,
    val goalDetail: String,
)
