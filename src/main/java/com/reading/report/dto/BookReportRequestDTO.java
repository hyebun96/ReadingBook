package com.reading.report.dto;

import com.reading.report.domain.BookReport;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BookReportRequestDTO {
    private String review;

    public BookReport toEntity() {
        return BookReport.builder()
                .review(review)
                .build();
    }
}
