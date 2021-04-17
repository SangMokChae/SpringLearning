package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Spring이 뜰때 읽음 // 자바 코드로 Spring Bean을 등록하는 방법
public class SpringConfig {

    @Bean // Spring Bean에 등록하라는 표시로 인식한다.
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean  // 상황에 따라 구현클래스 DB연결시에 이것만 바꾸어 주면 된다.
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
