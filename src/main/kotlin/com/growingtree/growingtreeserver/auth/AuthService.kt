package com.growingtree.growingtreeserver.auth

interface AuthService {
    fun sendCode(email: String): String

    fun signUp(
        email: String,
        password: String,
    )
}
