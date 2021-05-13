package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig(); // AppConfig를 생성해준다.
        MemberService memberService = appConfig.memberService(); // AppConfig로 부터 memberService를 받아온다.
        // memberService에서 생성자로 MemoryMemberRepository를 생성해준다.
        Member member = new Member(1L, "memberA", Grade.VIP); // 1L은 Long type
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("New Member = " +member.getName());
        System.out.println("find Member = " +findMember.getName());
    }
}
