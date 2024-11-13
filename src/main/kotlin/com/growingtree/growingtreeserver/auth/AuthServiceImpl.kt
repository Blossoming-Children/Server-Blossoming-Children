package com.growingtree.growingtreeserver.auth

import com.growingtree.growingtreeserver.auth.model.enums.ValidateType
import com.growingtree.growingtreeserver.auth.model.response.SignInResponse
import com.growingtree.growingtreeserver.domain.Motions
import com.growingtree.growingtreeserver.domain.Users
import com.growingtree.growingtreeserver.exception.CustomException
import com.growingtree.growingtreeserver.exception.messages.ErrorMessage
import com.growingtree.growingtreeserver.repository.MotionsRepository
import com.growingtree.growingtreeserver.repository.UsersRepository
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine

@Service
@RequiredArgsConstructor
class AuthServiceImpl(
    private val usersRepository: UsersRepository,
    private val motionsRepository: MotionsRepository,
    private val mailSender: JavaMailSenderImpl,
    private val templateEngine: SpringTemplateEngine,
) : AuthService {
    override fun sendCode(
        validateType: ValidateType,
        email: String,
    ): String {
        val authCode = createCode()
        val mailContent = mailSender.createMimeMessage()
        val title = "아이조아 - 본인 인증 코드입니다."

        if (validateType == ValidateType.SIGN_UP && !isUserExist(email)) {
            throw CustomException(ErrorMessage.USER_EXIST)
        }

        try {
            val mimeMessageHelper = MimeMessageHelper(mailContent, false, "utf-8")
            mimeMessageHelper.setTo(email)
            mimeMessageHelper.setSubject(title)
            mimeMessageHelper.setText(setContext(authCode), true)
            mailSender.send(mailContent)

            return authCode
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.FAILED_SENDING_MAIL)
        }
    }

    @Transactional
    override fun signUp(
        email: String,
        password: String,
        nickname: String,
    ) {
        if (!isUserExist(email)) {
            throw CustomException(ErrorMessage.USER_EXIST)
        }

        isEmailValid(email)
        isPasswordValid(password)

        try {
            val userId = usersRepository.save(
                Users(
                    email = email,
                    password = password,
                    name = nickname,
                ),
            ).id

            println(userId)

            if (userId != null) {
                motionsRepository.save(
                    Motions(
                        userId = userId,
                    )
                )
            } else {
                throw CustomException(ErrorMessage.FAILED_SIGN_UP)
            }
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.FAILED_SIGN_UP)
        }
    }

    override fun signIn(
        email: String,
        password: String,
    ): SignInResponse {
        val user = usersRepository.findUsersByEmail(email)
        when {
            user == null -> throw CustomException(ErrorMessage.FAILED_SIGN_IN)
            user.email != email -> throw CustomException(ErrorMessage.FAILED_SIGN_IN)
            user.password != password -> throw CustomException(ErrorMessage.FAILED_SIGN_IN)
            else -> return SignInResponse(user.id ?: -1)
        }
    }

    override fun findEmail(email: String) {
        if (isUserExist(email)) {
            throw CustomException(ErrorMessage.USER_NOT_FOUND)
        }
    }

    @Transactional
    override fun resetPassword(
        email: String,
        password: String,
    ) {
        usersRepository.findUsersByEmail(email) ?: throw CustomException(ErrorMessage.USER_NOT_FOUND)

        isPasswordValid(password)

        try {
            usersRepository.updatePasswordByEmail(email, password)
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.FAILED_RESET_PASSWORD)
        }
    }

    private fun isUserExist(email: String): Boolean = usersRepository.findUsersByEmail(email) == null

    private fun createCode(): String {
        val charset = ('0'..'9') + ('a'..'z') + ('A'..'Z')
        val code = List(6) { charset.random() }.joinToString("")
        return code
    }

    private fun setContext(code: String): String {
        val context = Context()
        context.setVariable("code", code)
        return templateEngine.process("ValidationCodeTemplate", context)
    }

    private fun isEmailValid(email: String): Boolean {
        if (email.contains(EMAIL_REGEX.toRegex())) {
            return true
        } else {
            throw CustomException(ErrorMessage.INVALID_EMAIL)
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        when {
            password.length < PASSWORD_LENGTH_MIN -> throw CustomException(ErrorMessage.INVALID_PASSWORD)
            !password.contains(UPPER_CASE_REGEX.toRegex()) && !password.contains(LOWER_CASE_REGEX.toRegex()) -> throw CustomException(ErrorMessage.INVALID_PASSWORD)
            !password.contains(NUMBER_REGEX.toRegex()) -> throw CustomException(ErrorMessage.INVALID_PASSWORD)
            else -> return true
        }
    }

    companion object {
        private const val PASSWORD_LENGTH_MIN = 10

        private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        private const val UPPER_CASE_REGEX = "[A-Z]"
        private const val LOWER_CASE_REGEX = "[a-z]"
        private const val NUMBER_REGEX = "[0-9]"
    }
}
