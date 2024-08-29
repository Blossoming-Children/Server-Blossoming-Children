package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.dto.stamps.response.GetGoalsResponse

interface StampService {
    fun getGoals(userId: Long): GetGoalsResponse

    fun addStamp(userId: Long)

    fun patchGoals(
        userId: Long,
        targetStamp: Int,
        detail: String,
    )

    fun deleteGoals(
        userId: Long,
        targetStamp: Int,
    )
}
