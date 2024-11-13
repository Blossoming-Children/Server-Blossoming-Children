package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.exception.CustomException
import com.growingtree.growingtreeserver.exception.messages.ErrorMessage
import com.growingtree.growingtreeserver.repository.UsersRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MyPageServiceImpl(
    val usersRepository: UsersRepository,
) : MyPageService {
    @Transactional
    override fun withdraw(
        userId: Long,
        password: String,
    ) {
        val user =
            try {
                usersRepository.findUsersById(userId)
            } catch (e: Exception) {
                throw CustomException(ErrorMessage.USER_NOT_FOUND)
            }

        if (user.password != password) {
            throw CustomException(ErrorMessage.INCORRECT_PASSWORD)
        }

        try {
            usersRepository.deleteUsersById(userId)
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.FAILED_WITHDRAW)
        }
    }
}
