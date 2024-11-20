package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UsersRepository : JpaRepository<Users, Long> {
    fun findUsersById(id: Long): Users

    fun findUsersByEmail(email: String): Users?

    @Modifying
    @Query("update Users set stampCount = stampCount + 1, updatedAt = now() where id = :id")
    fun updateUsersById(id: Long)

    @Modifying
    @Query("update Users set password = :password where email = :email")
    fun updatePasswordByEmail(
        email: String,
        password: String,
    )

    @Modifying
    @Query("update Users set name = :name where id = :userId")
    fun updateNameById(
        userId: Long,
        name: String,
    )

    @Modifying
    @Query("delete from Users where id = :userId")
    fun deleteUsersById(
        userId: Long,
    )
}
