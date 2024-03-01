package com.reading.report.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class BookReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int mybook_id;

    @Column
    private String review;

    @Column
    private String impressioin;

    @Column
    private String life_content;
}
