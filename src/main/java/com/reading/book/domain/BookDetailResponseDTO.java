package com.reading.book.domain;

import lombok.Getter;

@Getter
public class BookDetailResponseDTO {
    private String isbn;
    private String title;
    private String author;
    private String image;
    private String description;
}
