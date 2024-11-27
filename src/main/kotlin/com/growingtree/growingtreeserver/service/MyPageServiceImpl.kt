package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.domain.Users
import com.growingtree.growingtreeserver.dto.mypage.response.GetUserInfoResponse
import com.growingtree.growingtreeserver.dto.mypage.response.GetUserInfoResponse.Bookmark
import com.growingtree.growingtreeserver.dto.mypage.response.GetUserInfoResponse.LearningCount
import com.growingtree.growingtreeserver.enums.Motion
import com.growingtree.growingtreeserver.exception.CustomException
import com.growingtree.growingtreeserver.exception.messages.ErrorMessage
import com.growingtree.growingtreeserver.repository.AchievementsRepository
import com.growingtree.growingtreeserver.repository.BookmarksRepository
import com.growingtree.growingtreeserver.repository.EducationsRepository
import com.growingtree.growingtreeserver.repository.MotionsRepository
import com.growingtree.growingtreeserver.repository.UsersRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MyPageServiceImpl(
    private val usersRepository: UsersRepository,
    private val motionsRepository: MotionsRepository,
    private val bookmarksRepository: BookmarksRepository,
    private val achievementsRepository: AchievementsRepository,
    private val educationRepository: EducationsRepository,
) : MyPageService {
    @Transactional
    override fun withdraw(
        userId: Long,
        password: String,
    ) {
        val user = findUser(userId)

        if (user.password != password) {
            throw CustomException(ErrorMessage.INCORRECT_PASSWORD)
        }

        try {
            usersRepository.deleteUsersById(userId)
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.FAILED_WITHDRAW)
        }
    }

    override fun getUserProfile(userId: Long): GetUserInfoResponse {
        val user = findUser(userId)
        val motionLearningCount = getLearningCount(userId)
        val bookmarksList = getBookmarkList(userId)

        return GetUserInfoResponse(
            nickname = user.name,
            motionLearningCount = motionLearningCount,
            bookmarkList = bookmarksList,
        )
    }

    @Transactional
    override fun patchUserProfile(
        userId: Long,
        nickname: String,
    ) {
        try {
            usersRepository.updateNameById(userId, nickname)
        } catch (e: Exception) {
            e.printStackTrace()
            throw CustomException(ErrorMessage.FAILED_UPDATE_NAME)
        }
    }

    private fun findUser(userId: Long): Users {
        try {
            return usersRepository.findUsersById(userId)
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.USER_NOT_FOUND)
        }
    }

    private fun getLearningCount(userId: Long): List<LearningCount> {
        try {
            val motionsInfo = motionsRepository.findMotionsByUserId(userId)
            val learningCount =
                listOf(
                    LearningCount(
                        motion = Motion.WALK,
                        learningCount = motionsInfo.walk,
                    ),
                    LearningCount(
                        motion = Motion.RUN,
                        learningCount = motionsInfo.run,
                    ),
                    LearningCount(
                        motion = Motion.JUMP,
                        learningCount = motionsInfo.jump,
                    ),
                    LearningCount(
                        motion = Motion.CLAP,
                        learningCount = motionsInfo.clap,
                    ),
                    LearningCount(
                        motion = Motion.SIT,
                        learningCount = motionsInfo.sit,
                    ),
                    LearningCount(
                        motion = Motion.CROSS_ARMS,
                        learningCount = motionsInfo.cross_arms,
                    ),
                    LearningCount(
                        motion = Motion.SHAKE_HANDS,
                        learningCount = motionsInfo.shake_hands,
                    ),
                )
            return learningCount
        } catch (e: Exception) {
            e.printStackTrace()
            throw CustomException(ErrorMessage.FAILED_GET_MOTIONS_INFO)
        }
    }

    private fun getBookmarkList(userId: Long): List<Bookmark> {
        try {
            val bookmarkList = bookmarksRepository.findBookmarksByUserId(userId) ?: return listOf()
            val bookmarksList = mutableListOf<Bookmark>()

            for (bookmark in bookmarkList) {
                val videoInfo = educationRepository.findEducationsById(bookmark.eduId)
                val achievement =
                    achievementsRepository.getAchievementByUserIdAndEduId(
                        userId = userId,
                        eduId = bookmark.eduId,
                    )?.progress ?: 0
                bookmarksList.add(
                    Bookmark(
                        eduId = videoInfo.id ?: 0,
                        title = videoInfo.title,
                        videoUrl = videoInfo.url,
                        achievementRate = achievement,
                    ),
                )
            }

            return bookmarksList
        } catch (e: Exception) {
            e.printStackTrace()
            throw CustomException(ErrorMessage.FAILED_GET_BOOKMARK)
        }
    }
}
