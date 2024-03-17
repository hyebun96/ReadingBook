package com.reading.book.repository;

import com.reading.api.NaverBookAPI;
import com.reading.api.domain.NaverResultVO;
import com.reading.book.domain.Book;
import jdk.jfr.Description;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Log4j2
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private NaverBookAPI naverBookAPI;

    @Test
    @Description("BookRepository 저장 TEST")
    public void save() throws Exception {
        // Given
        NaverResultVO naverResultVO = naverBookAPI.searchBookOne("홍학의자리");
        Book book = naverResultVO.getItems().get(0);

        // When
        Book result = bookRepository.save(book);

        // Then
        log.info(result);

        assertEquals(book.getTitle(), "홍학의 자리 (정해연 장편소설)");
    }

    @Test
    public void searchTest() throws Exception {
        // Given
        String isbn = "9788954681155";

        // When
        Book book = bookRepository.findByIsbn(isbn).orElseThrow();

        // Then
        log.info(book);
        assertEquals(book.getTitle(), "홍학의 자리 (정해연 장편소설)");
    }

}