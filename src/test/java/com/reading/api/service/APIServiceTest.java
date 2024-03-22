package com.reading.api.service;

import com.reading.book.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
@Log4j2
class APIServiceTest {

    @Autowired
    private APIService apiService;

    @Test
    public void allSearchByTitleInNaverTest() throws IOException {
        // Given
        String title = "쇼펜하우어";

        // When
        Map<String, Object> result = apiService.allSearchByTitleInNaver(title, new PageRequestDTO());

        // Then
        log.info(result);
    }

    @Test
    public void allSearchByTitleInNaverTest2() throws IOException {
        // Given
        String title = "쇼펜하우어";
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .total(642)
                .start(11)
                .display(10)
                .build();


        // When
        Map<String, Object> result = apiService.allSearchByTitleInNaver(title, pageRequestDTO);

        // Then
        log.info(result);
    }

}