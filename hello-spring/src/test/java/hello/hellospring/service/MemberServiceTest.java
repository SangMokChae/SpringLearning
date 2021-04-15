package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest { // ctrl + shift + T는 Test파일을 자동으로 만들어 줍니다.

    MemberService memberService;
    MemoryMemberRepository memberRepository; // db clear를 위해서 만들어준다.

    @BeforeEach // 동작하기 전에 넣어 준다.
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // 같은 memberRepository 사용
    }

    @AfterEach
    public void afterEach() { // test할때마다 DB값을 초기화 해준다.
        memberRepository.clearStore();
    }

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
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// try - catch 대신 사용 // 람다식을 공부해보자

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 에러가 나오는지 성공 여부 확인


/*        try {
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. 12341231");
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