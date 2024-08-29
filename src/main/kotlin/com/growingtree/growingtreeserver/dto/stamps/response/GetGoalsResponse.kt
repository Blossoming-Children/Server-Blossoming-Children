package com.growingtree.growingtreeserver.dto.stamps.response

data class GetGoalsResponse(
    val userId: Long,
    val stampCount: Int,
    val goals: List<Goals>,
) {
    data class Goals(
        val targetStamp: Int,
        val detail: String,
    )
}
