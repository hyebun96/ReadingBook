package com.reading.report.dto;

import com.reading.report.domain.BookReport;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class BookReportRequestDTO {
    private String review;
    private String impression;
    private String lifeContent;

    public BookReport toEntity() {
        return BookReport.builder()
                .review(review)
                .impression(impression)
                .lifeContent(lifeContent)
                .build();
    }
}
