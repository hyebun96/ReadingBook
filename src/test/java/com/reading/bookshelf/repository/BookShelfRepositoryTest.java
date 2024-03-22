package com.reading.bookshelf.repository;

import com.reading.book.domain.Book;
import com.reading.book.repository.BookRepository;
import com.reading.bookshelf.domain.BookShelf;
import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    @DisplayName("내 책장에 도서 등록하기.")
    public void saveTest() throws Exception {
        // Given
        Long member_id = 1L;
        String isbn = "9788954681155";

        // isbn으로 도서의 정보를 가져옴
        Book book = bookRepository.findByIsbn(isbn).orElseThrow();
        Book book1 = Book.builder().isbn(isbn).build();

        // 내 서재에 등록할 데이터 build
        BookShelf bookShelf = BookShelf.builder()
                .member_id(member_id)
                .book(book)
                .build();

        // When
        // 내 서재에 책 등록
        BookShelf b = bookShelfRepository.save(bookShelf);

        // Then
        // 등록후 결과 확인
        log.info(b.toString());
        log.info(b.getBook().getIsbn(), isbn);
        log.info(b.getBook().getTitle(), "홍학의 자리 (정해연 장편소설)");
        assertEquals(book, book1);
        log.info(book1.toString());
    }

    @Test
    @DisplayName("내 서재 등록하기 버튼: 수행후, 내 서재에 이미 등록된 책인지 확인하는 테스트")
    public void existByBookTest() throws Exception {
        // Given
        // '/book/detail/9788954681155' 에서 내 서재에 등록하기 했을 경우, 수행
        String isbn = "9788954681155";
        // isbn으로 Book 가져옴
        Book book = bookRepository.findByIsbn(isbn).orElseThrow();
        log.info(book.getId());

        // When
        // 이 도서가 나의 책장에 등록되어 있는지 확인한다.
        Boolean exist = bookShelfRepository.existsByBook(book);

        // Then
        // 이 도서가 등록되어 있다면 true 반환후,이미 등록된 도서라고 뜸.
        // 등록되어 있지 않다면 false 반환후, 등록을 시켜줌
        assertEquals(exist, true);
    }

    @Test
    @DisplayName("회원이 책장에 등록된 도서가 있는지 확인하는 테스트")
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
    @DisplayName("책장: 회원 아이디로 등록된 책장의 도서들 전부 조회")
    public void findAllByMember_idTest() throws Exception {
        // Given
        Long member_id = 1L;

        // When & Then
       List<BookShelfListResponseDTO> list = bookShelfRepository.findByMember_id(member_id);

       list.stream().forEach(
               bookShelfListResponseDTO -> log.info(bookShelfListResponseDTO.getId())
       );
    }

}