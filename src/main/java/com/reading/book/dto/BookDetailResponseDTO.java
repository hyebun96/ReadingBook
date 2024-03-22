package com.reading.book.dto;

import lombok.Getter;

@Getter
public class BookDetailResponseDTO {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String image;
    private String description;
}
