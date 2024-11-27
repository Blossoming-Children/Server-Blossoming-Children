package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.domain.Educations
import com.growingtree.growingtreeserver.dto.video.response.GetEducationsResponse
import com.growingtree.growingtreeserver.exception.CustomException
import com.growingtree.growingtreeserver.exception.messages.ErrorMessage
import com.growingtree.growingtreeserver.repository.AchievementsRepository
import com.growingtree.growingtreeserver.repository.BookmarksRepository
import com.growingtree.growingtreeserver.repository.EducationsRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class VideoServiceImpl(
    private val educationsRepository: EducationsRepository,
    private val bookmarksRepository: BookmarksRepository,
    private val achievementsRepository: AchievementsRepository,
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

    private fun getBookmarksAndAchievements(educationsList: List<Educations>, userId: Long): List<GetEducationsResponse> {
        val list = mutableListOf<GetEducationsResponse>()
        try {

            for (i in educationsList) {
                val isBookmarked = bookmarksRepository.findBookmarksByUserIdAAndEduId(userId, i.id ?: 0)
                val progress = achievementsRepository.getAchievementByUserIdAndEduId(userId, i.id ?: 0)
                list.add(
                    GetEducationsResponse(
                        title = i.title,
                        url = i.url,
                        isBookmarked = isBookmarked != null,
                        achievement = progress?.progress ?: 0
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw CustomException(ErrorMessage.FAILED_GET_VIDEO_INFO)
        }
        return list
    }
}
