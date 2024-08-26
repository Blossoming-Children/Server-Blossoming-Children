package com.growingtree.growingtreeserver.dto.stamps.response

data class GetGoalsResponse(
    val userId: Long,
    val stampCount: Int,
    val goals: List<Goals>,
) {
    data class Goals(
        val targetStamps: Int,
        val detail: String,
    )
}
