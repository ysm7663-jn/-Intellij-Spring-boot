package com.example.hello.service;

import com.example.hello.domain.Member;
import com.example.hello.repository.MemberRepository;
import com.example.hello.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // Test 실행할 때 마다 객체가 각각 생성됨
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // memberService 입장에선 DI
        // why? >> 자신이 직접 new 하지 않기 때문
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        // AfterEach가 없을 경우 에러 발생
        // 데이터에 이미 들어가 있기 때문
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
            맞는 방식이긴 한데 위 방식이 더 간편
            try {
                memberService.join(member2);
                fail();
            } catch (IllegalStateException e) {
                assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            }
        */



        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}