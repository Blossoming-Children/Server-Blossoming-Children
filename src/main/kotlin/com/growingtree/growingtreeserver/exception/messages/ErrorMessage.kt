package com.growingtree.growingtreeserver.exception.messages

enum class ErrorMessage(
    val status: Int,
    val message: String,
) {
    // STAMP
    FAILED_GET_STAMP_INFO(404, "도장판 정보 불러오기를 실패했습니다."),
    FAILED_ADD_STAMP(404, "도장 추가를 실패했습니다"),
}
