package com.reading.report.dto;

import com.reading.report.domain.BookReport;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookReportResponseDTO {
    private Long id;
    private double scope;
    private String review;

    public BookReportResponseDTO(BookReport bookReport) {
        this.id = bookReport.getId();
        this.scope = bookReport.getBookScope().getScope();
        this.review = bookReport.getReview();
    }
}
