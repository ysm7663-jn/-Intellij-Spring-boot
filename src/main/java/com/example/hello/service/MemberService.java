package com.example.hello.service;

import com.example.hello.domain.Member;
import com.example.hello.repository.MemberRepository;
import com.example.hello.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service
// @Autowired를 사용하기 위해서 작성
// springcontainer에 memberService를 넣어놓는다
// @Component (Service안에 포함돼있음)

// jpa 사용시 꼭 필요
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    // Service와 Repository를 연결
    // 스프링 컨테이너에 존재하는 MemoryMemberRepository를 주입해 줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
        // ctrl + alt + M : 새로운 메소드로 뽑아 만들어 놓을 수 있다.
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // m == member
            // ifPresent : 값이 있을 경우 아래가 동작한다.
            throw  new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
