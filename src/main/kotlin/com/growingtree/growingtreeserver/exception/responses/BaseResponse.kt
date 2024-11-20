package com.growingtree.growingtreeserver.exception.responses

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.growingtree.growingtreeserver.exception.messages.SuccessMessage

@JsonPropertyOrder("status", "message", "result")
@JvmRecord
data class BaseResponse<T>(
    val status: Int,
    val message: String,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val result: T,
) {
    companion object {
        fun of(
            successMessage: SuccessMessage,
        ): BaseResponse<*> {
            return BaseResponse<Any?>(successMessage.status, successMessage.message, null)
        }

        fun <T> of(
            successMessage: SuccessMessage,
            result: T,
        ): BaseResponse<*> {
            return BaseResponse<Any?>(successMessage.status, successMessage.message, result)
        }
    }
}
