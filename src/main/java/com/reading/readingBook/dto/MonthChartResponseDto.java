package com.reading.readingBook.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MonthChartResponseDto {
    private String month;
    private Long count;

    @Builder
    public MonthChartResponseDto(String month, Long count) {
        this.month = month;
        this.count = count;
    }

    public MonthChartResponseDto() {
        this.count = 0L;
    }

    public MonthChartResponseDto(Long count) {
        this.count = count;
    }

    public void updateMonth(String month) {
        this.month = month;
    }

}
