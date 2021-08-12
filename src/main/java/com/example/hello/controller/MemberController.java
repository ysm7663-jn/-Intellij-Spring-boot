package com.example.hello.controller;

import com.example.hello.domain.Member;
import com.example.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// MemberController -> MemberService -> MemberRepository

@Controller
// spring이 관리함
// @Component (Controller안에 포함돼있음)
public class MemberController {
    // 2. 필드 주입
    // @Autowired private MemberService memberService;

    // 1. 생성자 주입 (권장)
    // : 의존관계가 실행중에 동적으로 변하는 경우는 거의 없기 때문
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


    // 3. setter 주입
    // 단점 : 중간에 변경이 있을 경우 큰 문제 발생
    // private MemberService memberService;

    // @Autowired
    // public void setMemberService(MemberService memberService) {
    //      this.memberService = memberService;
    //  }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
