package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.domain.Goals
import com.growingtree.growingtreeserver.dto.stamps.mapper.toGoalsResponseMapper
import com.growingtree.growingtreeserver.dto.stamps.response.GetGoalsResponse
import com.growingtree.growingtreeserver.exception.CustomException
import com.growingtree.growingtreeserver.exception.messages.ErrorMessage
import com.growingtree.growingtreeserver.repository.GoalsRepository
import com.growingtree.growingtreeserver.repository.UsersRepository
import jakarta.transaction.Transactional
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

    @Transactional
    override fun addStamp(userId: Long) {
        findUser(userId)
        try {
            usersRepository.updateUsersById(userId)
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.FAILED_ADD_STAMP)
        }
    }

    @Transactional
    override fun patchGoals(
        userId: Long,
        targetStamp: Int,
        detail: String,
    ) {
        findUser(userId)
        val goal: Goals? = findGoalDetail(userId, targetStamp)

        try {
            if (goal != null) {
                goal.id?.let { goalsRepository.updateGoalsById(goalId = it, detail = detail) }
            } else {
                goalsRepository.save(
                    Goals(
                        id = null,
                        userId = userId,
                        targetStamp = targetStamp,
                        detail = detail,
                    ),
                )
            }
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.FAILED_UPDATE_GOAL)
        }
    }

    @Transactional
    override fun deleteGoals(
        userId: Long,
        targetStamp: Int,
    ) {
        findUser(userId)
        try {
            with(goalsRepository.findGoalsByUserIdAndTargetStamp(userId, targetStamp)) {
                if (this != null) {
                    goalsRepository.delete(this)
                } else {
                    throw CustomException(ErrorMessage.GOAL_NOT_FOUND)
                }
            }
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.FAILED_DELETE_GOAL)
        }
    }

    private fun findUser(userId: Long) {
        try {
            usersRepository.findUsersById(userId)
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.USER_NOT_FOUND)
        }
    }

    private fun findGoalDetail(
        userId: Long,
        targetStamp: Int,
    ): Goals? {
        try {
            return goalsRepository.findGoalsByUserIdAndTargetStamp(userId, targetStamp)
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.SERVER_CONNECT_FAIL)
        }
    }
}
