package com.reading.book.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String discount;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String pubdate;

    @Column(length = 21845, nullable = true)
    private String description;

    @Builder
    public Book(Long id, String isbn, String title, String link, String image, String author, String discount, String publisher, String pubdate, String description) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.link = link;
        this.image = image;
        this.author = author;
        this.discount = discount;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.description = description;
    }
}
