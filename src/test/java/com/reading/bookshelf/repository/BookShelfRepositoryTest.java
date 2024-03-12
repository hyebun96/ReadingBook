package com.reading.bookshelf.repository;

import com.reading.bookshelf.domain.BookShelf;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Log4j2
class BookShelfRepositoryTest {

    @Autowired
    private BookShelfRepository bookShelfRepository;

    @Test
    public void findByIsbnAndMember_idTEST() {
        // Given

        Long member_id = 1L;
        String isbn = "9788960777330";

        // When & Then
        Long id = bookShelfRepository.findByIsbnAndMember_id(member_id, isbn);

        log.info("id값은 " + id);
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