package com.growingtree.growingtreeserver.repository

import com.growingtree.growingtreeserver.domain.Motions
import org.springframework.data.jpa.repository.JpaRepository

interface MotionsRepository : JpaRepository<Motions, Long>
