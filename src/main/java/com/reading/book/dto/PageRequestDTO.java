package com.reading.book.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequestDTO {

    private int total = 0;   // 결과값 페이지 수
    private int start = 1;   // 시작 값
    private int display = 10; // 한 페이지 당 보여줄 결과물.

}
