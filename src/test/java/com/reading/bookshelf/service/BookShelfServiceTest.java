package com.reading.bookshelf.service;

import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BookShelfServiceTest {

    @Autowired
    private BookShelfService bookShelfService;

    @Test
    public void saveTest() throws IOException {
        // Given
        String isbn = "9791192625553";

        // When
        Boolean existBookshlef = bookShelfService.save(isbn);

        // Then
        assertEquals(existBookshlef, true);
    }

    @Test
    public void findBymemberIdTest() throws IOException {
        // Given
        Long member_id = 1L;
        Long member_id2 = 10L;

        // When
        List<BookShelfListResponseDTO> bookShelves = bookShelfService.findByMember_id(member_id);
        List<BookShelfListResponseDTO> bookShelves2 = bookShelfService.findByMember_id(member_id2);

        // Then
        assertNotNull(bookShelves);
        assertNull(bookShelves2);

        bookShelves.stream().forEach(
                a -> log.info(a.toString())
        );
    }

}