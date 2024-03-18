package com.reading.bookshelf.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class BookShelfControllerTest {

    @Autowired
    private BookShelfController bookShelfController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getSaveTest() throws Exception {
        // Given
        String isbn = "9791192625553";

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/bookshelf/save/" + isbn))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andReturn();
    }

}