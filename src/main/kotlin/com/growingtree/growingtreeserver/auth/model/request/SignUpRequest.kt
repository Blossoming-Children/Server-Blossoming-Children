package com.growingtree.growingtreeserver.auth.model.request

data class SignUpRequest(
    val email: String,
    val password: String,
    val nickname: String,
)
