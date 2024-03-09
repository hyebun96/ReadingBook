package com.reading.book.domain;

import lombok.Getter;

@Getter
public class BookListResponseDTO {
    private String image;
    private String title;
    private String isbn;

    @Override
    public String toString() {
        return "BookListResponseDTO{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
