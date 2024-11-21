package com.navi.nbcampjavaspringcrudexample.api.controller;

import com.navi.nbcampjavaspringcrudexample.api.model.MemberRegisterRequest;
import com.navi.nbcampjavaspringcrudexample.database.entity.Member;
import com.navi.nbcampjavaspringcrudexample.database.repository.MemberRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;


    @PostMapping("/members")
    public Member registerMember(@RequestBody MemberRegisterRequest request) {
        Member member = new Member(request.getName(), LocalDateTime.now(), LocalDateTime.now());

        return memberRepository.save(member);
    }

    @GetMapping("/members/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("멤버가 없습니다!!!"));
    }

}
