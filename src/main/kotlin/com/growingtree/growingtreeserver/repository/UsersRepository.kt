package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<Users, Long> {
    fun findUsersById(id: Long): Users
}
