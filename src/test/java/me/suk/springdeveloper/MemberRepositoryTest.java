package me.suk.springdeveloper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

        @Sql("/insert_member.sql")
        @DisplayName("MemberRepository를 통해 member 테이블의 모든레코드(3개) 가져오기")
        @Test
        public void getAllMembers(){
            //준비 given

           //실행 when
            List<Member> members = memberRepository.findAll();

           //검증 then
            assertThat(members.size()).isEqualTo(4);
    }
}