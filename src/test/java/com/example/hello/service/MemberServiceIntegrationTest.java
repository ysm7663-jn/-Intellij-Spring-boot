package com.example.hello.service;

import com.example.hello.domain.Member;
import com.example.hello.repository.MemberRepository;
import com.example.hello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// 스프링 컨테이너와 테스트를 함께 실행한다
@Transactional
// Test 시작 전에 트랜잭션을 시작, Test가 종료되면 롤백(rollback)시킨다.
// DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다
// @AfterEach와 같은 역할

class MemberServiceIntegrationTest {

    // 단위 테스트(컨테이너 사용x)가 중요함
    // 통합 테스트 <<<< 단위 테스트
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

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

    }

}