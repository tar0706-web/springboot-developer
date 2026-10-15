package me.suk.springdeveloper;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

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

    @Sql("/insert_member.sql")
    @DisplayName("MemberRepositoryu를 통해 이름이 c인레코드 가져오기")
    @Test
    public void getMemberByname(){

        //준비 given

        //실행 when :
        Optional<Member> optMember = memberRepository.findByName("C");
        Member member = optMember.get();
        //검증 then
        assertThat(member.getId()).isEqualTo(3);
    };

        @Sql("/insert_member.sql")
        @DisplayName("MemberRepositoryu를 통해 id이 2 인레코드 가져오기")
        @Test
        public void getMemberById(){
            //준비 given

            //실행 when :
            Member member = memberRepository.findById(2L).get();
            //검증 then
            assertThat(member.getName()).isEqualTo("B");
        }

        @DisplayName("MemberRepository를 통해 이름 '임석원'인 레코드 추가하기")
        @Test
        public void saveMember(){
            //준비 given
            Member member = new Member("임석원");
            //실행 when
            memberRepository.save(member);
            //검증 then
            //member 테이블에서 모든 레코드를 select 했을 때 레코드의 수가 1이고
            //그 레코드의 이름 name칼럼 값이 '임석원'인지 확인
            List<Member> result = memberRepository.findAll();
            assertThat(result.size()).isEqualTo(1);
            assertThat(result.get(0).getName()).isEqualTo("임석원");
;       }

    @Sql("/insert_member.sql")
    @DisplayName("테이블 member 에서 id가 3인 레코드 삭제하기")
    @Test
    public void deleteById(){
        //준비 given

        //실행 when
        memberRepository.deleteById(3L);
        //검증 then
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(3);
    }


    @Sql("/insert_member.sql")
    @DisplayName("테이블 member의 모든 레코드 삭제하기")
    @Test
    public void deleteAll(){
        //준비 given
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(4);
        //실행 when
        memberRepository.deleteAll();
        //검증 then -> member 테이블에서 모든 레코드를 가져왔더니 하나도 없더라
        List<Member> member = memberRepository.findAll();
        assertThat(member.size()).isEqualTo(0);
    }


    @Sql("/insert_member.sql")
    @DisplayName("테이블 member name이ㅏ 'A', 'B'인 레코드를 한 번에 삽입하기")
    @Test
    public void saveMembers(){
        //준비 given
        //두 개의 Member 객체를 생성해서 List에 넣는다
        List<Member> members = List.of(new Member("A"), new Member("B"));
        //실행 when
        memberRepository.saveAll(members);
        //검증 then
        // 테이블에서 모든 레코드를 selection 했더니 걔수가 두개임을 검증
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
      //   List<Member> member = memberRepository.findAll();
      //  assertThat(member.size()).isEqualTo(2);
    }
    @Sql("/insert_member.sql")
    @DisplayName("변경감지 기능을 통해 Id가 2인 레코드의 이름을 'B', 'BB'로 변경하기")
 //   @Transactional  변경감지 쎠야하지만 @DataJpaTest 에 트렌젝션이 포함되있어서 안써도됌
    @Test
    public void update(){
        //준비 given
        Member member = memberRepository.findById(2L).get();
        //실행 when
        member.setName("BB");

        //검증 then
        member = memberRepository.findById(2L).get();
        assertThat(member.getName()).isEqualTo("BB");
    }
        }