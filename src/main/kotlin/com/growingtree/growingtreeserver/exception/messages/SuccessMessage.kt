package com.growingtree.growingtreeserver.exception.messages

enum class SuccessMessage(
    val status: Int,
    val message: String,
) {
    // AUTH
    SUCCESS_SEND_MAIL(200, "메일 전송에 성공했습니다."),
    SUCCESS_SIGN_UP(201, "회원가입에 성공했습니다."),
    SUCCESS_SIGN_IN(200, "로그인에 성공했습니다."),
    SUCCESS_FIND_EMAIL(200, "회원 조회에 성공했습니다."),
    SUCCESS_RESET_PASSWORD(200, "비밀번호 재설정에 성공했습니다."),

    // STAMP
    SUCCESS_GET_STAMP_INFO(200, "도장판 정보 불러오기를 성공했습니다."),
    SUCCESS_ADD_STAMP(201, "도장 추가를 성공했습니다."),
    SUCCESS_UPDATE_GOAL(201, "목표 수정을 성공했습니다."),
    SUCCESS_DELETE_GOAL(200, "목표 삭제를 성공했습니다."),

    // MY-PAGE
    SUCCESS_WITHDRAW(200, "계정 탈퇴에 성공했습니다."),
    SUCCESS_GET_PROFILE(200, "프로필 정보 조회를 성공했습니다."),
    SUCCESS_PATCH_NAME(200, "이름 수정을 성공했습니다."),
}
