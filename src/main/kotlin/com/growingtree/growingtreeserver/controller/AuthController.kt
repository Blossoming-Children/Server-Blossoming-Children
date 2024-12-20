package com.growingtree.growingtreeserver.controller

import com.growingtree.growingtreeserver.dto.auth.request.SendCodeRequest
import com.growingtree.growingtreeserver.dto.auth.request.SignInRequest
import com.growingtree.growingtreeserver.dto.auth.request.SignUpRequest
import com.growingtree.growingtreeserver.dto.auth.response.SendCodeResponse
import com.growingtree.growingtreeserver.exception.messages.SuccessMessage
import com.growingtree.growingtreeserver.exception.responses.BaseResponse
import com.growingtree.growingtreeserver.service.AuthService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
class AuthController(
    val authService: AuthService,
) {
    @PostMapping("/validate-email")
    fun validateEmail(
        @RequestBody sendCodeRequest: SendCodeRequest,
    ): BaseResponse<*> {
        val authCode =
            authService.sendCode(
                sendCodeRequest.validateType,
                sendCodeRequest.email,
            )
        return BaseResponse.of(SuccessMessage.SUCCESS_SEND_MAIL, SendCodeResponse(authCode))
    }

    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody signUpRequest: SignUpRequest,
    ): BaseResponse<*> {
        authService.signUp(
            signUpRequest.email,
            signUpRequest.password,
            signUpRequest.nickname,
        )
        return BaseResponse.of(SuccessMessage.SUCCESS_SIGN_UP)
    }

    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody signInRequest: SignInRequest,
    ): BaseResponse<*> {
        val signInResponse = authService.signIn(signInRequest.email, signInRequest.password)
        return BaseResponse.of(SuccessMessage.SUCCESS_SIGN_IN, signInResponse)
    }

    @GetMapping("/find-email")
    fun findEmail(
        @RequestParam email: String,
    ): BaseResponse<*> {
        authService.findEmail(email)
        return BaseResponse.of(SuccessMessage.SUCCESS_FIND_EMAIL, "")
    }

    @PatchMapping("reset-password")
    fun resetPassword(
        @RequestBody signUpRequest: SignUpRequest,
    ): BaseResponse<*> {
        authService.resetPassword(signUpRequest.email, signUpRequest.password)
        return BaseResponse.of(SuccessMessage.SUCCESS_RESET_PASSWORD, "")
    }
}
