package com.reading.book.service;

import com.reading.book.dto.BookDetailResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Log4j2
class BookServiceTest {

    @Autowired
    private BookService bookService;


    @Test
    public void saveTest() throws IOException {

        // Given
        String isbn = "9788954681155";

        // When & Then
        bookService.save(isbn);

    }

    @Test
    @DisplayName("도서 검색 디테일.")
    public void searchDetailTest() throws IOException {
        // Given
        String isbn = "9788954681155";

        // When
        BookDetailResponseDTO bookDetailResponseDTO = bookService.searchDetail(isbn);

        // Then
        assertEquals(bookDetailResponseDTO.getAuthor(), "정해연");
        log.info(bookDetailResponseDTO.toString());

    }

}