package com.reading.bookshelf.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Log4j2
class BookShelfServiceTest {

    @Autowired
    private BookShelfService bookShelfService;

    @Test
    public void saveTest() throws IOException {
        // Given
        String isbn = "9788960777330";

        // When
        Boolean existBookshlef = bookShelfService.save(isbn);

        // Then
        assertEquals(existBookshlef, true);
    }

}