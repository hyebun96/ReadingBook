package com.reading.bookshelf.service;

import com.reading.bookshelf.domain.BookShelf;
import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import com.reading.member.domain.Member;
import com.reading.member.repository.MemberRepository;
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

    @Autowired
    private MemberRepository memberRepository;

    public Member findMember() {
        // 데이터에 있는 uuid 테스트 번호로 넣기
        Long uuid = 3403991514L;
        return memberRepository.findByUuid(uuid).orElseThrow();
    }

    @Test
    public void saveTest() throws IOException {
        // Given
        String isbn = "9791192625553";

        // When
        Boolean existBookshlef = bookShelfService.save(isbn, findMember());

        // Then
        assertEquals(existBookshlef, true);     // 등록완료
        assertEquals(existBookshlef, false);    // 이미 등록된 도서일 경우
    }

    @Test
    public void findBymemberIdTest() throws IOException {
        // Given
        Member member1 = findMember();
        Member member2 = memberRepository.findByUuid(3415867084L).orElseThrow();

        // When
        List<BookShelfListResponseDTO> bookShelves = bookShelfService.findByMember(member1);
        List<BookShelfListResponseDTO> bookShelves2 = bookShelfService.findByMember(member2);

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
        Long bookShelfId = 1L;

        // When
        BookReport bookReport = bookReportRepository.findById(bookReportId).orElseThrow();
        BookShelf bookShelf = bookShelfService.saveBookReport(bookReport, bookShelfId);

        // Then
        log.info(bookShelf.toString());
    }

}