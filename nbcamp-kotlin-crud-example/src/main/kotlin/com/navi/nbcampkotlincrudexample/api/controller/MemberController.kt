package com.navi.nbcampkotlincrudpractice.api.controller

import com.navi.nbcampkotlincrudpractice.api.model.member.RegisterMemberRequest
import com.navi.nbcampkotlincrudpractice.api.service.MemberService
import com.navi.nbcampkotlincrudpractice.database.entity.Member
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/{id}")
    fun findById(
        @PathVariable("id") id: Long,
    ): Member = memberService.findById(id)

    @GetMapping
    fun findAll(): List<Member> = memberService.findAll()

    @PostMapping("/register")
    fun register(
        @RequestBody request: RegisterMemberRequest,
    ): Member =
        memberService.register(
            request.username,
            request.password,
            request.email,
            LocalDateTime.now(),
        )
}
