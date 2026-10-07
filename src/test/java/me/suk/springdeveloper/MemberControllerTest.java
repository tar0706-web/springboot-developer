package me.suk.springdeveloper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.* ;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void cleanup(){
        memberRepository.deleteAll();
    }

    @DisplayName("회원정보 리스트 요청")
    @Test
    void getAllMembers() throws Exception {
        //준비(GIVEN)
        //회원 등록 (리포지토리를 이용해서 DB테이블에 직접 삽입)
        Member m = new Member("sukwon");
        Member savedMember = memberRepository.save(m); //insert

        // 실행(WHEN)
        // 회원 리스트 요청
        //ResultActions result = mockMvc.perform(get("/member").accept(MediaType.APPLICATION_JSON);

        //검증(THEN)
        //준비단계에서 등록한 회원 정보가 반환되어야한다
        //result.andExpect(status().isOk())
        //        .andExpect(jsonPath("$[0].id").value(1))
       //         .andExpect(jsonPath("$[0].name").value(savedMember.getName());

    }
}