package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception {
        //givin
//        Member member = new Member();
//        member.setUsername("헐크");
//        //when
//        Long saveId = memberRepository.save(member);
//        Member findMember = memberRepository.find(saveId);
//        //then
//        Assertions.assertThat(findMember.getId().equals(member.getId()));
//        Assertions.assertThat(findMember.getUsername().equals(member.getUsername()));
//        Assertions.assertThat(findMember).isEqualTo(member);
//        System.out.println("findMember == member :: " + (findMember == member));

    }


}