package com.reading.bookshelf.service;

import com.reading.book.domain.Book;
import com.reading.book.repository.BookRepository;
import com.reading.bookshelf.domain.BookShelf;
import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import com.reading.bookshelf.repository.BookShelfRepository;
import com.reading.report.domain.BookReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookShelfService {

    private final BookShelfRepository bookshelfRepository;
    private final BookRepository bookRepository;

    public Boolean save(String isbn) throws IOException {

        Book book = bookRepository.findByIsbn(isbn).orElseThrow();

        Boolean existBookShelf = bookshelfRepository.existsByBook(book);

        if(existBookShelf) {
            log.info("이미 내 책장에 등록된 도서입니다... " + isbn);
            return true;
        }

        BookShelf bookshelf = BookShelf.builder()
                .book(book)
                .member_id(1L)
                .build();

        BookShelf result = bookshelfRepository.save(bookshelf);

        return false;
    }

    public List<BookShelfListResponseDTO> findByMember_id(Long member_id) throws IOException {

        String count = bookshelfRepository.existsByMember_id(member_id);

        List<BookShelfListResponseDTO> list = null;

        if(Integer.parseInt(count) > 0) {
            list = bookshelfRepository.findByMember_id(member_id);
        }

        return list;
    }

    public BookShelf saveBookReport(BookReport bookReport, Long bookShelfId) throws IOException {

        BookShelf bookShelf = bookshelfRepository.findById(bookShelfId).orElseThrow();

        bookShelf.addBookReport(bookReport);

       return bookshelfRepository.save(bookShelf);
    }

}
