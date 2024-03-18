package com.reading.bookshelf.repository;

import com.reading.book.domain.Book;
import com.reading.book.repository.BookRepository;
import com.reading.bookshelf.domain.BookShelf;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Log4j2
class BookShelfRepositoryTest {

    @Autowired
    private BookShelfRepository bookShelfRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void findAllTest() throws  Exception{
        //Given

        // When & Then
        List<BookShelf> bookShelves = bookShelfRepository.findAll();

        bookShelves.stream().forEach(
                bookShelf -> log.info(bookShelf.toString())
        );
    }

    @Test
    public void saveTest() throws Exception {
        // Given
        Long member_id = 1L;
        String isbn = "9788954681155";

        Book book = bookRepository.findByIsbn(isbn).orElseThrow();

        BookShelf bookShelf = BookShelf.builder()
                .member_id(member_id)
                .book(book)
                .build();

        // When
        BookShelf b = bookShelfRepository.save(bookShelf);

        // Then

        log.info(b.toString());
    }

    @Test
    public void existByBookTest() throws Exception {
        // Given
        String isbn = "9788954681155";
        Book book = bookRepository.findByIsbn(isbn).orElseThrow();
        log.info(book.getId());

        // When
        Boolean exist = bookShelfRepository.existsByBook(book);

        // Then
        assertEquals(exist, true);
    }

    @Test
    public void existByMemberIdTest() throws Exception {
        // Given
        Long member_id = 1L;

        // When
        String exist = bookShelfRepository.existsByMember_id(member_id);
        Boolean result = Integer.parseInt(exist) > 1 ? true : false;

        // Then
        assertEquals(result, true);
    }

    @Test
    public void findAllByMember_idTest() throws Exception {
        // Given
        Long member_id = 1L;

        // When & Then
       List<BookShelf> list = bookShelfRepository.findByMember_id(member_id);

       list.stream().forEach(
               bookShelf -> log.info(bookShelf.toString())
       );
    }


}