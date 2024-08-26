package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.dto.stamps.response.GetGoalsResponse

interface StampService {
    fun getGoals(userId: Long): GetGoalsResponse
}
