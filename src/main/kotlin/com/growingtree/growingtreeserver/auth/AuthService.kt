package com.growingtree.growingtreeserver.auth

import com.growingtree.growingtreeserver.auth.model.response.SignInResponse

interface AuthService {
    fun sendCode(email: String): String

    fun signUp(
        email: String,
        password: String,
    )

    fun signIn(
        email: String,
        password: String,
    ): SignInResponse

    fun findEmail(email: String)

    fun resetPassword(
        email: String,
        password: String,
    )
}
