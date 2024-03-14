package com.reading.report.repository;

import com.reading.report.domain.BookReport;
import com.reading.report.dto.BookReportRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Log4j2
class BookReportRepositoryTest {

    @Autowired
    private BookReportRepository bookReportRepository;

    @DisplayName("독서노트저장")
    @Test
    public void saveReport() {
        // Given
        BookReport bookReport = BookReport.builder()
                .mybookId(1)
                .review("줄거리")
                .impression("느낀점")
                .lifeContent("삶의적용")
                .build();

        // When
        BookReport result = bookReportRepository.save(bookReport);

        // Then
        assertEquals(bookReport, result);
        assertEquals(bookReport.getReview(), result.getReview());
    }

    @DisplayName("독서노트수정")
    @Test
    public void updateReport() {
        // Given
        BookReport updateId = bookReportRepository.findById(18L).orElseThrow();

        BookReportRequestDTO bookReportRequestDTO = BookReportRequestDTO.builder()
                .review("re...update...줄거리")
                .impression("re...update...느낀점")
                .lifeContent("re...update...삶의적용")
                .build();

        updateId.setReview(bookReportRequestDTO.getReview());
        updateId.setImpression(bookReportRequestDTO.getImpression());
        updateId.setLifeContent(bookReportRequestDTO.getLifeContent());

        // When
        BookReport result = bookReportRepository.save(updateId);

        // Then
        log.info(result.toString());
    }


    @DisplayName("독서노트삭제")
    @Test
    public void deleteReport() {
        bookReportRepository.deleteById(16L);
    }

    @DisplayName("독서노트검색")
    @Test
    public void selectReport() {
        // Given
        Long id = 15L;

        // When
        BookReport BookReport = bookReportRepository.findById(id).orElseThrow();

        // Then
        assertEquals(BookReport.getReview(), "update...줄거리");
        assertEquals(BookReport.getImpression(), "update...느낀점");
        assertEquals(BookReport.getLifeContent(), "update...삶의적용");
    }

}