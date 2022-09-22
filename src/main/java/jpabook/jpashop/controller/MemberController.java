package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    
    // 회원 가입 폼
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }
    
    // 회원가입 처리
    @PostMapping("/members/new")
    public String create(MemberForm form) {

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    // 회원 목록 조회
    @GetMapping("/members")
    public String list(Model model) {
        // 실무에서는 entity를 DTO로 변환해서 DTO를 화면에 전달하는 걸 매우 권장!!!
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        

        return "members/memberList";
    }

}
