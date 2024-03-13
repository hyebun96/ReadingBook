package com.reading.bookshelf.repository;

import com.reading.bookshelf.domain.BookShelf;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Log4j2
class BookShelfRepositoryTest {

    @Autowired
    private BookShelfRepository bookShelfRepository;

    @Test
    public void findByIsbnAndMember_idTEST() throws  Exception{
        // Given

        Long member_id = 1L;
        String isbn = "9788960777330";

        // When & Then
        String existCount = bookShelfRepository.countByIdAndIsbn(member_id, isbn);

        assertEquals(existCount, "1");
    }

    @Test
    public void selectAllTest() throws IOException {
        // Given

        // When & Then
        List<BookShelf> bookShelves = bookShelfRepository.findAll();

        bookShelves.stream().forEach(
                bookshlf -> log.info(bookshlf.toString())
        );

    }

}