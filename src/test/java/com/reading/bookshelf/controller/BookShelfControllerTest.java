package com.reading.bookshelf.controller;

import com.reading.member.domain.Member;
import com.reading.member.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class BookShelfControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    public Member findMember() {
        // 데이터에 있는 uuid 테스트 번호로 넣기
        Long uuid = 3403991514L;
        return memberRepository.findByUuid(uuid).orElseThrow();
    }

    @Test
    public void getSaveTest() throws Exception {
        // Given
        String isbn = "9791192625553";
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("member", findMember());

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/bookshelf/save/" + isbn)
                        .session(mockHttpSession))
                        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                        .andReturn();
    }

    @Test
    public void listTest() throws Exception {
        // Given
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("member", findMember());

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/bookshelf/list")
                        .session(mockHttpSession))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();
    }

}