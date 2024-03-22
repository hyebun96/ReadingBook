package com.reading.api.contorller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class APIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void searchTest() throws  Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/search")
                        .param("title", "쇼펜하우어")
                        .param("total", "0")
                        .param("start", "2")
                        .param("display", "10"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}