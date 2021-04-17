package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // Component 스캔(연결을 위한 내장됨)
public class MemberController {

    private MemberService memberService; // 생성자를 통해서 MemberService에 주입이된다.

//    @Autowired
//    public void setMemberService(MemberService memberService) { // setter생성시에 final을 지워주어야 한다. (단점으로 존재 = public으로 존재 권장 안함)
//        this.memberService = memberService;
//    }

    //    @Autowired private MemberService memberService; // 필드 주입 (좋지 않음) / 변경이 불가능 해진다.

    @Autowired // 연관관계를 쓸 수있게 해준다. // 생성자 주입 권장 방법
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
//        memberService.setMemberRepository();
//      이러한 방식을 사용하면 public방식으로 누구나 오픈이 가능해진다. // 개발에서 호출하지 않아야될 메소드는 호출되지 않게 해야된다.
    }

    @GetMapping("/members/new")
    public String createForm() { // templates에서 찾는다.
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //redirect를 글자로 형식화
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
