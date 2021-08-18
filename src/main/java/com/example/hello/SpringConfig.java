package com.example.hello;

import com.example.hello.repository.JdbcMemberRepository;
import com.example.hello.repository.MemberRepository;
import com.example.hello.repository.MemoryMemberRepository;
import com.example.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

// spring bean: 상황에 따라 구현 클래스를 변경해야 하는 경우 유리

// spring에 관리되지 않는 것은 @Autowired가 작동하지 않는다.
// new 객체 또한 @Autowired가 작동하지 않는다.
// ∴ spring bean에 등록된 것만이 @Autowired가 적용된다.
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    // spring bean이 application.properties에서 설정을 확인하고 직접 생성해줌
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 스프링 빈에 @Bean을 등록
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // JDBC 사용전
        // return new MemoryMemberRepository();

        // 확장
        return new JdbcMemberRepository(dataSource);
    }

}
