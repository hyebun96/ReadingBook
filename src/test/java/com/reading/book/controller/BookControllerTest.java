package com.reading.book.controller;

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
class BookControllerTest {

    @Autowired
    private BookController bookController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void searchTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/book/search")
                        .param("title", "쇼펜하우어"))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void searchDetailTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/book/detail/9791192625553"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void saveTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/book/save/9791192625553"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

}