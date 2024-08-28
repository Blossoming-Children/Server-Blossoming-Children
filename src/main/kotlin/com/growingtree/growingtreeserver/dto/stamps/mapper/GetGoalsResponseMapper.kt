package com.growingtree.growingtreeserver.dto.stamps.mapper

import com.growingtree.growingtreeserver.domain.Goals
import com.growingtree.growingtreeserver.dto.stamps.response.GetGoalsResponse

fun Goals.toGoalsResponseMapper(): GetGoalsResponse.Goals =
    GetGoalsResponse.Goals(
        targetStamp = this.targetStamp,
        detail = this.detail,
    )
