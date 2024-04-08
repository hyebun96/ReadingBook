package com.reading.bookshelf.service;

import com.reading.book.domain.Book;
import com.reading.book.repository.BookRepository;
import com.reading.bookshelf.domain.BookShelf;
import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import com.reading.bookshelf.repository.BookShelfRepository;
import com.reading.member.domain.Member;
import com.reading.member.repository.MemberRepository;
import com.reading.report.domain.BookReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookShelfService {

    private final BookShelfRepository bookshelfRepository;
    private final BookRepository bookRepository;

    private final MemberRepository memberRepository;

    public Map<String, Object> save(String isbn, Member member) throws IOException {

        Book book = bookRepository.findByIsbn(isbn).orElseThrow();
        BookShelf responseBookShelf = null;
        Map<String, Object> responseMap = new HashMap<>();

        Boolean existBookShelf = bookshelfRepository.existsByBookAndMember(book, member);

        if(existBookShelf) {
            log.info("이미 내 책장에 등록된 도서입니다... " + isbn);

            responseBookShelf = bookshelfRepository.findBookShelfByBookAndMember(book, member).orElseThrow();
            responseMap.put("message", "이미 내 서재에 등록된 도서 입니다.");
            responseMap.put("existMessage", true);
        } else {
            BookShelf requestBookShelf = BookShelf.builder()
                    .book(book)
                    .member(member)
                    .build();

            responseBookShelf = bookshelfRepository.save(requestBookShelf);
            responseMap.put("message", "내 서재에 도서가 등록이 되었습니다.");
            responseMap.put("existMessage", true);
        }

        responseMap.put("bookshelf", responseBookShelf);
        responseMap.put("bookshelfId", responseBookShelf.getId());

        return responseMap;
    }

    public List<BookShelfListResponseDTO> findByMember(Member member) throws IOException {

        String count = bookshelfRepository.existsByMember(member);

        List<BookShelfListResponseDTO> list = null;

        if(Integer.parseInt(count) > 0) {
            list = bookshelfRepository.findByMember(member);
        }

        return list;
    }

    public BookShelf saveBookReport(BookReport bookReport, Long bookShelfId) throws IOException {

        BookShelf bookShelf = bookshelfRepository.findById(bookShelfId).orElseThrow();

        bookShelf.addBookReport(bookReport);

       return bookshelfRepository.save(bookShelf);
    }

}
