package com.growingtree.growingtreeserver.service

interface MyPageService {
    fun withdraw(
        userId: Long,
        password: String,
    )
}
