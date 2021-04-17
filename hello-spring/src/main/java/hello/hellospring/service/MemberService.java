package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service // 넣어 주면 Spring이 올라올때 Service를 인식해서, 비즈니스 로직 생성
public class MemberService { // 순수 자바 Class

    private final MemberRepository memberRepository;

//    @Autowired // 비권장
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Autowired // Spring이 뜰때 생성자를 호출 해준다. // Spring Container에 올라가는 값들만 동작하게 한다. // Bean이 등록된 것에만 작동한다.
    public MemberService(MemberRepository memberRepository) { // 외부에서 사용할 수 있게 바꾸어 준다. // DI = Dependency Injection
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복회원X
//        Optional<Member> result = memberRepository.findByName(member.getName()); // 과거와는 다르게 if(result = null)이렇게 바로 사용하기 보다는 Optional로 한번 감싸서 그안의 method를 활용하여 사용한다.
//        result.ifPresent(m -> { // result값이 있으면 아래의 것이 동작한다. // Optional안에 여러 method가 존재하여 사용이 가능하다.
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        // Member member1 = result.get(); // 바로 꺼낼 수 있음 (권장하지 않음)
        // Member member1 = result.orElseGet(); // 값이 있으면 꺼내고 없으면 꺼내지 않는 식으로 쓸수 있다.

        validateDuplicateMember(member); // 중복 회원 검증 // ctrl + alt + shift + T로 아래의 method를 생성한다.
        memberRepository.save(member);
        return member.getId(); // id만 반환한다.
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // Optional을 사용하지 않고 바로 사용이 가능하다.
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
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
