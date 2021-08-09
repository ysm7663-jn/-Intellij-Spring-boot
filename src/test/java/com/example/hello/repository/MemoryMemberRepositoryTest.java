package com.example.hello.repository;

import com.example.hello.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    // 테스트는 순서에 의존적이지 않게 수행돼야 한다.
    // 따라서, 테스트 하나 수행후에는 데이터를 정리해줘야 한다.

    MemoryMemberRepository repository = new MemoryMemberRepository();


    @Test
    public void save() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);
        // optional 일 경우 .get()을 붙인다.
        Member result = repository.findById(member.getId()).get();

        // System.out.println("result = " + (result == member));

        // Assertions(기대값, 결과값); >> 매번 출력분으로 확인 할 수 없기 때문에 사용
        // Assertions.assertEquals(member, result);
        // 요즘은 아래를 사용하는 추세
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);  // True
        // assertThat(result).isEqualTo(member2);  // False

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2); // True
        // assertThat(result.size()).isEqualTo(3); // False
    }

}
