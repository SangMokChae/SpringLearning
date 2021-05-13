package hello.core.member;

public class MemberServiceImpl implements MemberService {  // 관례 구현체가 하나일때는 impl로 많이 사용

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository; // 인터페이스에만 의존

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // null 값이면 나머지 구현체들에 문제가 발생한다.
    // 그래서 구현 객체를 설정해준다.

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    } // join에서 save를 호출하면 MemoryMemberRepository에 있는 save를 호출해준다. 위에 new 설정때문에

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
