package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false)
    @DisplayName("회원가입 테스트")
    public void joinTest() throws Exception {
        //givin
        Member member = new Member();
        member.setName("ironMan");
        //when
        Long saveId = memberService.join(member);
        //then
        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    @DisplayName("회원 중복 체크")
    public void duplicateMemberCheck() throws Exception {
        //givin
        Member mem1 = new Member();
        mem1.setName("hulk");

        Member mem2 = new Member();
        mem2.setName("hulk");

        //when
        memberService.join(mem1);
        try {
            memberService.join(mem2);
        }catch(IllegalStateException e) {
            return;
        }

        //then
//        fail("예외가 발생해야 한다!!!!!!!!!");
    }

}