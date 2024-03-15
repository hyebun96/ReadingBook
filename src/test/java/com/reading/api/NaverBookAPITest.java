package com.reading.api;

import com.reading.api.domain.FromBookToReportDTO;
import com.reading.api.domain.NaverResultVO;
import com.reading.book.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Log4j2
class NaverBookAPITest {

    @Autowired
    private NaverBookAPI naverBookAPI;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void searchBookAll() throws IOException {
        // Given
        String title = "쇼펜하우어";

        // When & Then
        NaverResultVO naverResultVO = naverBookAPI.searchBookAll(title);
        List<Book> list = naverResultVO.getItems();

        // Then
        list.stream().forEach(
                a -> log.info(a.toString())
        );

    }

    @Test
    public void searchBookOne() throws IOException {
        // Given
        String title = "홍학의 자리";

        // When
        NaverResultVO naverResultVO = naverBookAPI.searchBookOne(title);
        List<Book> list = naverResultVO.getItems();

        // Then
        list.stream().forEach(
                a -> log.info(a.toString())
        );

        Book book = list.get(0);
        Assertions.assertEquals(book.getTitle(), "홍학의 자리 (정해연 장편소설)");
    }

    @Test
    public void fromBookToReportDTOTest() throws IOException {
        // Given
        String title = "홍학의 자리";

        // When
        NaverResultVO naverResultVO = naverBookAPI.searchBookOne(title);
        List<Book> list = naverResultVO.getItems();

        // Then
        FromBookToReportDTO fromBookToReportDTO = modelMapper.map(list.get(0), FromBookToReportDTO.class);
        log.info(fromBookToReportDTO.toString());

    }

}
