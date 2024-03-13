package com.reading.bookshelf.service;

import com.reading.bookshelf.domain.BookShelf;
import com.reading.bookshelf.repository.BookShelfRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookShelfService {

    private final BookShelfRepository bookshelfRepository;

    public Boolean save(String isbn) throws IOException {

        String existBookShelf = bookshelfRepository.countByIdAndIsbn(1L, isbn);

        if(existBookShelf.equals("1")) {
            log.info("이미 내 책장에 등록된 도서입니다... " + isbn);
            return true;
        }

        BookShelf bookshelf = BookShelf.builder()
                .isbn(isbn)
                .member_id(1L)
                .build();

        BookShelf result = bookshelfRepository.save(bookshelf);

        return false;
    }


}
