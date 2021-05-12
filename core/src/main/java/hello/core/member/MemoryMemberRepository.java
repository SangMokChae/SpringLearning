package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    // DB확정 전

    // 저장소 찾기를 위한. hsahMap
    private static Map<Long, Member> store = new HashMap<>();

    // 실무에서는 콩커레헤쉬 맵을 사용한다. (동시성 문재때문에)

    @Override
    public void save(Member member) {
        // 원래라면 오류처리 등을 해주어야 한다.
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
