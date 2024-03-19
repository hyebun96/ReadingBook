package com.reading.book.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookDetailResponseDTO {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String image;
    private String description;
}
