package com.growingtree.growingtreeserver.controller

import com.growingtree.growingtreeserver.dto.mypage.request.WithdrawRequest
import com.growingtree.growingtreeserver.exception.messages.SuccessMessage
import com.growingtree.growingtreeserver.exception.responses.BaseResponse
import com.growingtree.growingtreeserver.service.MyPageService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/my-page")
@RequiredArgsConstructor
class MyPageController(
    val myPageService: MyPageService,
) {
    @DeleteMapping("/withdraw")
    fun withdrawUser(
        @RequestHeader("Authorization") userId: Long,
        @RequestBody withdrawRequest: WithdrawRequest,
    ): BaseResponse<*> {
        myPageService.withdraw(userId, withdrawRequest.password)
        return BaseResponse.of(SuccessMessage.SUCCESS_WITHDRAW)
    }
}