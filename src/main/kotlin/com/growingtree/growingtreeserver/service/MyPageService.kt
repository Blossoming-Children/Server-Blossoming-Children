package com.growingtree.growingtreeserver.service

import com.growingtree.growingtreeserver.dto.mypage.response.GetUserInfoResponse

interface MyPageService {
    fun withdraw(
        userId: Long,
        password: String,
    )

    fun getUserProfile(userId: Long): GetUserInfoResponse

    fun patchUserProfile(
        userId: Long,
        nickname: String,
    )
}
