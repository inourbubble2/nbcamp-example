package com.navi.nbcampkotlincrudpractice.database.repository

import com.navi.nbcampkotlincrudpractice.database.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>
