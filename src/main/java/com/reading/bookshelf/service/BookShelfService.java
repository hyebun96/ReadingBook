package com.reading.bookshelf.service;

import com.reading.book.domain.Book;
import com.reading.book.repository.BookRepository;
import com.reading.bookshelf.domain.BookShelf;
import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import com.reading.bookshelf.repository.BookShelfRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookShelfService {

    private final BookShelfRepository bookshelfRepository;
    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

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
            List<BookShelf> bookShelves = bookshelfRepository.findByMember_id(member_id);
            list = bookshelfTrans(bookShelves);
        }

        return list;
    }

    public List<BookShelfListResponseDTO> bookshelfTrans(List<BookShelf> bookShelves) {

        List<BookShelfListResponseDTO> list = new ArrayList<>();

        bookShelves.stream().forEach(
                bookShelf -> list.add(
                        BookShelfListResponseDTO.builder()
                        .id(bookShelf.getId())
                        .member_id(bookShelf.getMember_id())
                        .image(bookShelf.getBook().getImage())
                        .title(bookShelf.getBook().getTitle())
                        .isbn(bookShelf.getBook().getIsbn())
                        .build())
        );

        return list;
    }

}
