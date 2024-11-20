package com.growingtree.growingtreeserver.exception.responses

import com.growingtree.growingtreeserver.exception.messages.ErrorMessage
import lombok.Builder

@Builder
class BaseException(
    val status: Int,
    val message: String,
) {
    companion object {
        fun of(
            errorMessage: ErrorMessage,
        ): BaseException {
            return BaseException(errorMessage.status, errorMessage.message)
        }
    }
}
