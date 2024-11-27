package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Educations
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface EducationsRepository : JpaRepository<Educations, Int> {
    fun findEducationsById(id: Long): Educations

    @Query("select s from Educations s ")
    fun getEducations(): List<Educations>
}