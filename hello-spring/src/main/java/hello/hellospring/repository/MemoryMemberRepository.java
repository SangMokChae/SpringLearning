package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository // Spring이 인식해줌
public class MemoryMemberRepository implements MemberRepository  {

    private static Map<Long, Member> store = new HashMap<>();  // 실무에서는 동시속 문제때문에 컨커런텐시브(?)를 사용해야 한다.
    private static long sequence = 0L; // sequence는 단순히 0,1,2 key 값을 생성해준는 아이이다. 동시속 문제로 인해 어텀롱(?)을 사용한다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id값 세팅
        store.put(member.getId(), member); // id가 map에 저장됨
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // optional.ofNullable()로 감싸서 null 이어도 값이 가능하게 해준다. - 클리이언트에서 작업이 가능하게 해준다.
    }

    @Override
    public Optional<Member> findByName(String name) { // 람다식으로 인해 roof를 돌려 준다.
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // member.getName이 paremeter값으로 넘어온 값이랑 같은지 비교
                .findAny(); // 값이 같으면 반환한다. // findAny는 하나로도 찾는 것이다. 결과는 optional로 반환 // 찾는 값이 없으면 optional에 null이 포함되서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 실무에서 list를 많이 사용한다. // store.values = Member에 해당한다.
    }

    public void clearStore() { // store안에를 싹 비운다.
        store.clear();
    }
}
// 동작하는지 확인하기 위해 테스트 케이스를 작성하는 것이다.