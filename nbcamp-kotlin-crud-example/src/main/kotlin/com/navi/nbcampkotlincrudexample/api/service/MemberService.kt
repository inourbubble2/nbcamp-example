package com.navi.nbcampkotlincrudpractice.api.service

import com.navi.nbcampkotlincrudpractice.database.entity.Member
import com.navi.nbcampkotlincrudpractice.database.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun findAll(): List<Member> = memberRepository.findAll()

    fun findById(id: Long): Member = memberRepository.findByIdOrNull(id) ?: throw IllegalStateException("Member not found.")

    fun register(
        username: String,
        password: String,
        email: String,
        createdAt: LocalDateTime,
    ): Member {
        val member = Member(username, password, email, createdAt)
        return memberRepository.save(member)
    }
}
