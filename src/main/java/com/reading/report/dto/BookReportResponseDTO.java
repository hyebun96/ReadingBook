package com.reading.report.dto;

import com.reading.report.domain.BookReport;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookReportResponseDTO {
    private Long id;
    private Long bookShelfId;
    private double scope;
    private String review;
    private String impression;
    private String lifeContent;

    public BookReportResponseDTO(BookReport bookReport) {
        this.id = bookReport.getId();
        this.bookShelfId = bookReport.getBookShelf().getId();
        this.scope = bookReport.getBookScope().getScope();
        this.review = bookReport.getReview();
        this.impression = bookReport.getImpression();
        this.lifeContent = bookReport.getLifeContent();
    }
}
