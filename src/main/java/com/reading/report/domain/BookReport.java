package com.reading.report.domain;

import com.reading.bookshelf.domain.BookShelf;
import com.reading.common.BaseEntity;
import com.reading.report.dto.BookReportRequestDTO;
import com.reading.scope.domain.BookScope;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class BookReport extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 줄거리
    @Column
    private String review;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "bookReport", cascade = CascadeType.ALL)
    private BookScope bookScope;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "bookReport", cascade = CascadeType.ALL)
    private BookShelf bookShelf;

    @Builder
    // 불필요한 생성자 제거
    // null 체크 가능
    public BookReport(String review) {
        this.review = review;
    }

    public void addBookScope(BookScope bookScope) {
        this.bookScope = bookScope;
    }

    public void updateBookReport(BookReportRequestDTO requestDTO) {
        this.review = requestDTO.getReview();
    }
}
