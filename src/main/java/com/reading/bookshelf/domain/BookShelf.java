package com.reading.bookshelf.domain;

import com.reading.book.domain.Book;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookShelf {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long member_id;

    @ManyToOne()
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

}
