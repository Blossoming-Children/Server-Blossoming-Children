package com.growingtree.growingtreeserver.auth.model.request

import com.growingtree.growingtreeserver.auth.model.enums.ValidateType

data class SendCodeRequest(
    val validateType: ValidateType,
    val email: String,
)
