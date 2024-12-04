package com.growingtree.growingtreeserver.dto.video.request

import com.growingtree.growingtreeserver.enums.Motion

data class PatchMotionCountRequest(
    val motion: Motion,
)
