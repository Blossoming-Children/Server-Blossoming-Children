package com.growingtree.growingtreeserver.auth

import com.growingtree.growingtreeserver.exception.messages.SuccessMessage
import com.growingtree.growingtreeserver.exception.responses.BaseResponse
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
class AuthController(
    val authService: AuthService
) {
    @PostMapping("/validate-email")
    fun validateEmail(@RequestBody email: String): BaseResponse<*> {
        val authCode = authService.sendCode(email)
        return BaseResponse.of(SuccessMessage.SUCCESS_SEND_MAIL, authCode)
    }
}
