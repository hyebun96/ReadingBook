package com.reading.report.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookReportDTO {
    private Long id;
    private int mybook_id;
    private String review;
    private String impressioin;
    private String life_content;
}
