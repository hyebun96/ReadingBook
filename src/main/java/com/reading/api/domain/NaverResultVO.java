package com.reading.api.domain;

import com.reading.book.domain.Book;
import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NaverResultVO {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Book> items;
}
