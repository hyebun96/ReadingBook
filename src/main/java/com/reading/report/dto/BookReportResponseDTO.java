package com.reading.report.dto;

import com.reading.report.domain.BookReport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookReportResponseDTO {
    private Long id;
    private int mybookId;
    private double scope;
    private String review;
    private String impression;
    private String lifeContent;

    public BookReportResponseDTO(BookReport bookReport) {
        this.id = bookReport.getId();
        this.mybookId = bookReport.getMybookId();
        this.scope = bookReport.getBookScope().getScope();
        this.review = bookReport.getReview();
        this.impression = bookReport.getImpression();
        this.lifeContent = bookReport.getLifeContent();
    }
}
