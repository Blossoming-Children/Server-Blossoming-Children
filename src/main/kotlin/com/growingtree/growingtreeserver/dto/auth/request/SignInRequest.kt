package com.growingtree.growingtreeserver.dto.auth.request

data class SignInRequest(
    val email: String,
    val password: String,
)
