package com.growingtree.growingtreeserver.dto.auth.request

import com.growingtree.growingtreeserver.enums.ValidateType

data class SendCodeRequest(
    val validateType: ValidateType,
    val email: String,
)
