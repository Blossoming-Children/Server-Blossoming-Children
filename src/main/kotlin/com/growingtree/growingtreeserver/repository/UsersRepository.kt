package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UsersRepository : JpaRepository<Users, Long> {
    fun findUsersById(id: Long): Users

    @Modifying
    @Query("update Users set stampCount = stampCount + 1, updatedAt = now() where id = :id")
    fun updateUsersById(id: Long)
}
