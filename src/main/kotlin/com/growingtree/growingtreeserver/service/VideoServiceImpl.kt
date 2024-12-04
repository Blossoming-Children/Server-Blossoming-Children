package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.domain.Achievements
import com.growingtree.growingtreeserver.domain.Educations
import com.growingtree.growingtreeserver.dto.video.response.GetEducationDetailResponse
import com.growingtree.growingtreeserver.dto.video.response.GetEducationsResponse
import com.growingtree.growingtreeserver.enums.Motion
import com.growingtree.growingtreeserver.exception.CustomException
import com.growingtree.growingtreeserver.exception.messages.ErrorMessage
import com.growingtree.growingtreeserver.repository.AchievementsRepository
import com.growingtree.growingtreeserver.repository.BookmarksRepository
import com.growingtree.growingtreeserver.repository.EducationsRepository
import com.growingtree.growingtreeserver.repository.MotionsRepository
import com.growingtree.growingtreeserver.repository.VideosRepository
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class VideoServiceImpl(
    private val educationsRepository: EducationsRepository,
    private val videosRepository: VideosRepository,
    private val bookmarksRepository: BookmarksRepository,
    private val achievementsRepository: AchievementsRepository,
    private val motionsRepository: MotionsRepository,
) : VideoService {
    override fun getEducations(userId: Long): List<GetEducationsResponse> {
        try {
            val educationList = educationsRepository.getEducations()
            return getBookmarksAndAchievements(educationList, userId)
        } catch (e: Exception) {
            e.printStackTrace()
            throw CustomException(ErrorMessage.FAILED_GET_VIDEO_INFO)
        }
    }

    override fun getVideos(eduId: Long): List<GetEducationDetailResponse> {
        try {
            val videoList = videosRepository.findVideosByEduId(eduId)
            return videoList.map { i ->
                GetEducationDetailResponse(
                    title = i.title,
                    description = i.description,
                    url = i.url,
                )
            }
        } catch (e: Exception) {
            throw CustomException(ErrorMessage.FAILED_GET_VIDEO_INFO)
        }
    }

    @Transactional
    override fun patchMotionResult(
        userId: Long,
        motion: Motion,
    ) {
        try {
            motionsRepository.findMotionsByUserId(userId)
            motionsRepository.updateMotionsByUserId(
                userId = userId,
                motion = motion.name,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw CustomException(ErrorMessage.FAILED_UPDATE_MOTIONS)
        }
    }

    @Transactional
    override fun patchEducationProgress(
        userId: Long,
        educationId: Long,
        progress: Int,
    ) {
        try {
            val education = achievementsRepository.getAchievementByUserIdAndEduId(userId, educationId)

            if (education != null) {
                achievementsRepository.updateProgressByUserIdAndEduId(
                    userId = userId,
                    eduId = educationId,
                    progress = progress,
                )
            } else {
                achievementsRepository.save(
                    Achievements(
                        userId = userId,
                        eduId = educationId,
                        progress = progress,
                    ),
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw CustomException(ErrorMessage.FAILED_UPDATE_ACHIEVEMENT)
        }
    }

    private fun getBookmarksAndAchievements(
        educationsList: List<Educations>,
        userId: Long,
    ): List<GetEducationsResponse> {
        val list = mutableListOf<GetEducationsResponse>()
        try {
            for (i in educationsList) {
                val isBookmarked = bookmarksRepository.findBookmarksByUserIdAndEduId(userId, i.id ?: 0)
                val progress = achievementsRepository.getAchievementByUserIdAndEduId(userId, i.id ?: 0)
                list.add(
                    GetEducationsResponse(
                        eduId = i.id ?: 0,
                        title = i.title,
                        url = i.url,
                        isBookmarked = isBookmarked != null,
                        achievement = progress?.progress ?: 0,
                    ),
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw CustomException(ErrorMessage.FAILED_GET_VIDEO_INFO)
        }
        return list
    }
}
