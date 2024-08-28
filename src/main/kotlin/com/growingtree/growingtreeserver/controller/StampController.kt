package com.growingtree.growingtreeserver.controller

import com.growingtree.growingtreeserver.dto.stamps.request.PatchGoalsRequest
import com.growingtree.growingtreeserver.exception.messages.SuccessMessage
import com.growingtree.growingtreeserver.exception.responses.BaseResponse
import com.growingtree.growingtreeserver.service.StampService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stamp")
@RequiredArgsConstructor
class StampController(
    val stampService: StampService,
) {
    @GetMapping("")
    fun getStampInfo(
        @RequestHeader("Authorization") userId: Long,
    ): BaseResponse<*> {
        val response = stampService.getGoals(userId)
        return BaseResponse.of(SuccessMessage.SUCCESS_GET_STAMP_INFO, response)
    }

    @PostMapping("")
    fun addStamp(
        @RequestHeader("Authorization") userId: Long,
    ): BaseResponse<*> {
        stampService.addStamp(userId)
        return BaseResponse.of(SuccessMessage.SUCCESS_ADD_STAMP)
    }

    @PatchMapping("")
    fun patchGoalDetail(
        @RequestHeader("Authorization") userId: Long,
        @RequestBody patchGoalsRequest: PatchGoalsRequest,
    ): BaseResponse<*> {
        stampService.patchGoals(userId = userId, targetStamp = patchGoalsRequest.targetStamp, detail = patchGoalsRequest.goalDetail)
        return BaseResponse.of(SuccessMessage.SUCCESS_UPDATE_GOAL)
    }

    @DeleteMapping("/{targetStamp}")
    fun deleteGoal(
        @RequestHeader("Authorization") userId: Long,
        @PathVariable("targetStamp") targetStamp: Int,
    ): BaseResponse<*> {
        stampService.deleteGoals(userId, targetStamp)
        return BaseResponse.of(SuccessMessage.SUCCESS_DELETE_GOAL)
    }
}
