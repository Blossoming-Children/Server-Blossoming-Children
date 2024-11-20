package com.growingtree.growingtreeserver.dto.auth.request

data class SignUpRequest(
    val email: String,
    val password: String,
    val nickname: String,
)
