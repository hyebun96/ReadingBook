package com.reading.book.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class BookScope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String member_id;

    @Column
    private int scope;

    @Column
    private String isbn;
}
