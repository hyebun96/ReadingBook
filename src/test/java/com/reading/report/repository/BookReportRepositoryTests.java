package com.reading.report.repository;

import com.reading.report.dto.BookReportDTO;
import com.reading.report.service.BookReportService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BookReportRepositoryTests {

    @Autowired
    private BookReportService bookReportService;

    @Test
    public void testRegister() {

        log.info(bookReportService.getClass().getName());

        BookReportDTO bookReportDTO = BookReportDTO.builder()
                .mybook_id(1)
                .review("Test Review")
                .impressioin("Test Impressioin")
                .life_content("Test LifeContent")
                .build();

        Long id = bookReportService.register(bookReportDTO);

        log.info("id: " + id);
    }
}
