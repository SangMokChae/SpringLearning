package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions; // jupiter보다 편하게 사용하는 것이다.
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { // 굳이 public으로 하지 않아도 된다. 어디에다 사용 할 것이 아니기 때문에 // testcase의 장점은 한번에 돌릴 수 있다.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 실행이 끝날때 마다 실행 callback method
    public void afterEach() {
        repository.clearStore(); // test가 끝날때마다 한번씩 저장소를 지운다. //매우 중요
    }

    @Test // test로 실행이 가능해진다.
    public void save() {
        Member member = new Member();
        member.setName("spring"); // spring|" -> |에서 command + shift + enter

        repository.save(member); // repository에서 member를 저장한다.

        Member result =  repository.findById(member.getId()).get(); // 반환 type이 optional이다. // get 으로 꺼내는 것이 좋은 방법은 아니지만 test에서는 가능

        // 저장한 거랑 db에서 꺼낸 거랑 완전히 같으면 true 이다.
        // System.out.println("result = " + (result == member));
        // Assertions.assertEquals(member, null); // jupiter version  // member는 기대값이다. actual은 실제 값이다.
        assertThat(member).isEqualTo(result); // 앞에 나오는 것이 실제 값이고 뒤에 나오는 것이 기대값이다.
    }

    @Test
    public void findName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); // spring1을 찾기 // get을 사용하면 optional을 까서 꺼낼 수 있다고 한다.

        assertThat(result).isEqualTo(member1);
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

        assertThat(result.size()).isEqualTo(2); // result list안에 존재 하는 값은 2개 여야 한다.
    }
}
// 전부 테스트 실행했을때 error가 나는 이유는? 순서대로 작동하지 않아서!!!! 모든 테스트는 순서랑 상관없이 설게하게끔 해야한다.
// 순서 우선적으로 설게하면 절대 안된다. // 테스트가 끝나면 데이터가 clear해 주어야 한다. 깔끔하게 (이전 데이터가 들어 있을 수도 있기 때문이다.)
// test 주도 개발 test를 먼저 실행하고 구현 클래스를 만들어 주는 것 TDD // 여럿이서 만들어 주는 거는 test코드로 해야 한다.
