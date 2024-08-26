package com.growingtree.growingtreeserver.exception.messages

enum class SuccessMessage(
    val status: Int,
    val message: String,
) {
    // STAMP
    SUCCESS_GET_STAMP_INFO(200, "도장판 정보 불러오기를 성공했습니다."),
}
