package com.reading.readingBook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MonthChartResponseDto {
    private String month;
    private Long count;

    public MonthChartResponseDto() {
        this.count = 0L;
    }

    public MonthChartResponseDto(Long count) {
        this.count = count;
    }
}
