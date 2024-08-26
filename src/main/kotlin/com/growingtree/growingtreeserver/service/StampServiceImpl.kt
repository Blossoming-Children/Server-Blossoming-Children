package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.dto.stamps.mapper.toGoalsResponseMapper
import com.growingtree.growingtreeserver.dto.stamps.response.GetGoalsResponse
import com.growingtree.growingtreeserver.exception.CustomException
import com.growingtree.growingtreeserver.exception.messages.ErrorMessage
import com.growingtree.growingtreeserver.repository.GoalsRepository
import com.growingtree.growingtreeserver.repository.UsersRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class StampServiceImpl(
    val usersRepository: UsersRepository,
    val goalsRepository: GoalsRepository,
) : StampService {
    override fun getGoals(userId: Long): GetGoalsResponse {
        try {
            val goals =
                goalsRepository.findGoalsByUserId(userId).map {
                    it.toGoalsResponseMapper()
                }
            val stampCount = usersRepository.findUsersById(userId).stampCount
            return GetGoalsResponse(userId, stampCount, goals)
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.FAILED_GET_STAMP_INFO)
        }
    }
}
