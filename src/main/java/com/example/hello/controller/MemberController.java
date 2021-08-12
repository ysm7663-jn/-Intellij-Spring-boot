package com.example.hello.controller;

import com.example.hello.domain.Member;
import com.example.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// MemberController -> MemberService -> MemberRepository

@Controller
// spring이 관리함
// @Component (Controller안에 포함돼있음)
public class MemberController {

    private final MemberService memberService;

    @Autowired
    // Service에 @Service
    // Repository에 @Repository
    // Controller 와 Service를 연결 : DI(스프링이 의존관계를 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // @Service나 @Repository가 없는 경우
    // SpringConfig에 있는 @Bean을 참조하여 찾아간다.
}
