package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.dto.auth.response.SignInResponse
import com.growingtree.growingtreeserver.enums.ValidateType

interface AuthService {
    fun sendCode(
        validateType: ValidateType,
        email: String,
    ): String

    fun signUp(
        email: String,
        password: String,
        nickname: String,
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
