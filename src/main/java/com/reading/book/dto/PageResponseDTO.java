package com.reading.book.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageResponseDTO {

    private int total;   // 결과값 페이지 수
    private int start;   // 시작 값
    private int display; // 한 페이지 당 보여줄 결과물.

    private int totalPage;  // 전체 페이지
    private int page;       // 시작 페이지
    private int nextStart;  // 다음 시작값
    private Boolean next;   // 다음 페이지 존재 유무

    public void setPageAndNext() {
        this.totalPage = (total % display == 0)?  (total / display) : (total / display) +1;
        this.page = (start / display) + 1;
        this.nextStart = (page * display) + 1;
        this.next = this.total > this.nextStart;
    }

    public PageResponseDTO() {
        this.total = 0;
        this.start = 1;
        this.display = 0;
        totalPage = 0;
        page = 0;
        nextStart = 0;
        next = null;
    }
}
