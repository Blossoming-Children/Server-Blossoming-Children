package com.growingtree.growingtreeserver.auth

import com.growingtree.growingtreeserver.auth.model.enums.ValidateType
import com.growingtree.growingtreeserver.auth.model.response.SignInResponse

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
