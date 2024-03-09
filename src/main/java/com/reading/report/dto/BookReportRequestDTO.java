package com.reading.report.dto;

import com.reading.report.domain.BookReport;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookReportRequestDTO {
    private int mybookId;
    private String review;
    private String impression;
    private String lifeContent;

    public BookReport toEntity() {
        return BookReport.builder()
                .mybookId(mybookId)
                .review(review)
                .impression(impression)
                .lifeContent(lifeContent)
                .build();
    }
}
