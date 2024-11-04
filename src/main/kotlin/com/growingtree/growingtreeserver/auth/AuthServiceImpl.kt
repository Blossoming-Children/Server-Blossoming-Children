package com.growingtree.growingtreeserver.auth

import com.growingtree.growingtreeserver.domain.Users
import com.growingtree.growingtreeserver.exception.CustomException
import com.growingtree.growingtreeserver.exception.messages.ErrorMessage
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
    private val mailSender: JavaMailSenderImpl,
    private val templateEngine: SpringTemplateEngine,
) : AuthService {
    override fun sendCode(email: String): String {
        val authCode = createCode()
        val mailContent = mailSender.createMimeMessage()
        val title = "아이조아 - 본인 인증 코드입니다."

        if (!isUserExist(email)) {
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
    ) {
        if (!isUserExist(email)) {
            throw CustomException(ErrorMessage.USER_EXIST)
        }

        try {
            usersRepository.save(
                Users(
                    email = email,
                    password = password,
                ),
            )
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.FAILED_SIGN_UP)
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
}
