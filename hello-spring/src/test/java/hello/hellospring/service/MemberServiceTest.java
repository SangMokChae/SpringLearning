package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest { // ctrl + shift + T는 Test파일을 자동으로 만들어 줍니다.

    MemberService memberService = new MemberService();


    @Test // test는 한글로 바꾸어도 된다.
    void 회원가입() {
        // given 주어짐
        Member member = new Member();
        member.setName("hello");

        // when 실행함
        Long saveId = memberService.join(member);

        // then 결과가 나와야 된다.
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
        memberService.join(member2);

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}