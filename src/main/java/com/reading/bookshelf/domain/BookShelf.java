package com.reading.bookshelf.domain;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;


@Getter
@Table(name = "bookshelf")
@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookShelf {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    @Setter
    private Long member_id;

    @Column(length = 500, nullable = false)
    private String isbn;
}
