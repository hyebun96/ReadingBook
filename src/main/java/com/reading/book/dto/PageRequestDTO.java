package com.reading.book.dto;

import lombok.*;

@Getter
public class PageRequestDTO {

    private int total;       // 결과값 페이지 수
    private int start;       // 시작 값
    private int display;     // 한 페이지 당 보여줄 결과물.

    @Builder
    public PageRequestDTO() {
        this.total = 0;
        this.start = 1;
        this.display = 10;
    }
}
