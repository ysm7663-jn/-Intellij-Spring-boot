package com.example.hello.repository;

import com.example.hello.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // Optional : java8, null로 반환될 경우 return 값을 매꿔줌
    List<Member> findAll();
}
