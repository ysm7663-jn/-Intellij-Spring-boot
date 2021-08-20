package com.example.hello.repository;

import com.example.hello.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JpaRepository가 대부분 기본적으로 제공해준다

    // JpaRepository에 없는 경우는 직접 생성
    @Override
    Optional<Member> findByName(String name);
}
