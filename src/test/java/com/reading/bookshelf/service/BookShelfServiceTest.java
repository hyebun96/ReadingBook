package com.reading.bookshelf.service;

import com.reading.bookshelf.domain.BookShelf;
import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import com.reading.report.domain.BookReport;
import com.reading.report.repository.BookReportRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
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

    @Autowired
    private BookReportRepository bookReportRepository;

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

    @Test
    @DisplayName("내 책장에 독후감 저장하기!")
    public void saveBookReportTest() throws IOException {
        // Given
        Long bookReportId = 1L;
        Long bookShelfId = 3L;

        // When
        BookReport bookReport = bookReportRepository.findById(bookReportId).orElseThrow();
        BookShelf bookShelf = bookShelfService.saveBookReport(bookReport, bookShelfId);

        // Then
        log.info(bookShelf.toString());
    }

}